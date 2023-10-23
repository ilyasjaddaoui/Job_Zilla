package com.example.jobzilla_backend.services.impl;

import com.example.jobzilla_backend.dtos.CandidateDto;
import com.example.jobzilla_backend.entities.Candidate;
import com.example.jobzilla_backend.entities.Job;
import com.example.jobzilla_backend.entities.User;
import com.example.jobzilla_backend.exceptions.JobApiException;
import com.example.jobzilla_backend.repositories.CandidateRepository;
import com.example.jobzilla_backend.repositories.JobRepository;
import com.example.jobzilla_backend.repositories.UserRepository;
import com.example.jobzilla_backend.services.CandidateService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository candidateRepository;
    private ModelMapper modelMapper;
    private JobRepository jobRepository;
    private final UserRepository userRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository, ModelMapper modelMapper, JobRepository jobRepository,
                                UserRepository userRepository) {
        this.candidateRepository = candidateRepository;
        this.modelMapper = modelMapper;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CandidateDto apply(Long jobId, CandidateDto candidateDto) {

        // map to entity and save
        Candidate candidate = mapToEntity(candidateDto);

        // get job by id
        Job job = jobRepository.findById(jobId).orElseThrow(()-> new JobApiException(HttpStatus.BAD_REQUEST, "Job not found"));

        // get user by id
//        User user = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not found: " + userId));
//
//        candidate.setUser(user);
        candidate.setJob(job);

        // save candidate to database
        Candidate newCandidate = candidateRepository.save(candidate);
        return mapToDto(newCandidate);
    }

    @Override
    public CandidateDto getCandidatesById(Long jobId, Long candidateId) {
        // get job by id
        Job job = jobRepository.findById(jobId).orElseThrow(()-> new JobApiException(HttpStatus.BAD_REQUEST, "Job not found"));

        // get candidate by id
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(()-> new JobApiException(HttpStatus.BAD_REQUEST, "candidate not found"));

        // find user by id
//        User user = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not found: " + userId));
//
//        if(!candidate.getUser().getId().equals(user.getId())){
//            throw new JobApiException(HttpStatus.BAD_REQUEST, "No candidate found");
//        }

        if(!candidate.getJob().getId().equals(job.getId())){
            throw new JobApiException(HttpStatus.BAD_REQUEST, "Candidates does not apply to job");
        }
        return mapToDto(candidate);
    }

    @Override
    public List<CandidateDto> getCandidatesByJobId(Long jobId) {
        List<Candidate> candidates = candidateRepository.findByJobId(jobId);
        return candidates.stream().map(candidate -> mapToDto(candidate)).collect(Collectors.toList());
    }







    /*
        * Mapping Entity to Dto
    */

    private CandidateDto mapToDto(Candidate candidate){
        CandidateDto candidateDto = modelMapper.map(candidate, CandidateDto.class);
        return candidateDto;
    }

    private Candidate mapToEntity(CandidateDto candidateDto){
        Candidate candidate = modelMapper.map(candidateDto, Candidate.class);
        return candidate;
    }
}
