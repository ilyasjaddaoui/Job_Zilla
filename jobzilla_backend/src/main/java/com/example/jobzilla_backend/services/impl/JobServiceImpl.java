package com.example.jobzilla_backend.services.impl;

import com.example.jobzilla_backend.dtos.JobDto;
import com.example.jobzilla_backend.dtos.JobResponse;
import com.example.jobzilla_backend.entities.Job;
import com.example.jobzilla_backend.entities.User;
import com.example.jobzilla_backend.exceptions.JobApiException;
import com.example.jobzilla_backend.repositories.JobRepository;
import com.example.jobzilla_backend.repositories.UserRepository;
import com.example.jobzilla_backend.services.JobService;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public JobDto saveJob(JobDto jobDto, Long userId) {
        // map to entity and save
        Job job = mapToEntity(jobDto);
        // find user by id
        User user = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not found: " + userId));

        job.setUser(user);

        Job savedJob = jobRepository.save(job);

        return mapToDto(savedJob);
    }

    @Override
    public JobResponse getAllPosts(int pageNo, int pageSize, String keyword) {
        // Create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Job> jobs;
        List<Job> jobList;

        if (!StringUtils.isEmpty(keyword)) {
            log.debug("Executing SQL query for keyword: {}", keyword);
            // Use the custom repository method for searching by keyword
            jobs = jobRepository.findByJobTitleContaining(keyword, pageable);
            jobList = jobs.getContent();
        } else {
            log.debug("Executing SQL query to retrieve all jobs");
            // If no keyword provided, retrieve all jobs
            jobs = jobRepository.findAll(pageable);
            jobList = jobs.getContent();
        }

        List<JobDto> content = jobList.stream().map(this::mapToDto).collect(Collectors.toList());

        JobResponse jobResponse = new JobResponse();
        jobResponse.setContent(content);
        jobResponse.setPageNo(jobs.getNumber());
        jobResponse.setPageSize(jobs.getSize());
        jobResponse.setTotalElements(jobs.getTotalElements());
        jobResponse.setTotalPages(jobs.getTotalPages());
        jobResponse.setLast(jobs.isLast());

        return jobResponse;
    }


//    public JobResponse getAllPosts(int pageNo, int pageSize, String keyword) {
//        // create pageable instance
//        Pageable pageable= PageRequest.of(pageNo,pageSize);
//
//       Page<Job> jobs = jobRepository.findAll(pageable);
//
//        //get content for page object
//        List<Job> jobList = jobs.getContent();
//
//        // Use the custom repository method for searching by keyword
//        Page<Job> jobSearch = jobRepository.findByJobTitleContaining(keyword, pageable);
//
//        // Get content for the page object
//
//       List<JobDto> content = jobList.stream().map(job -> mapToDto(job)).collect(Collectors.toList());
//
//        JobResponse jobResponse = new JobResponse();
//        jobResponse.setContent(content);
//        jobResponse.setPageNo(jobs.getNumber());
//        jobResponse.setPageSize(jobs.getSize());
//        jobResponse.setTotalElements(jobs.getTotalElements());
//        jobResponse.setTotalPages(jobs.getTotalPages());
//        jobResponse.setLast(jobs.isLast());
//        return jobResponse;
//    }


    @Override
    public JobDto getJobById(Long jobId) {
        // find job by id
        Job job = jobRepository.findById(jobId).orElseThrow(()-> new JobApiException(HttpStatus.BAD_REQUEST, "Job not found"));

        // find user by id
//        User user = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not found: " + userId));
//
//        if(!job.getUser().getId().equals(user.getId())){
//            throw new JobApiException(HttpStatus.BAD_REQUEST, "No job found");
//        }
        return mapToDto(job);
    }

    @Override
    public JobDto updateJob(JobDto jobDto, Long id) {
        Job job = jobRepository.findById(id).orElseThrow(()-> new JobApiException(HttpStatus.BAD_REQUEST, "Job not found"));
        job.setCompanyName(jobDto.getCompanyName());
        job.setCompanyLogo(jobDto.getCompanyLogo());
        job.setJobTitle(jobDto.getJobTitle());
        job.setJobCategory(jobDto.getJobCategory());
        job.setJobType(jobDto.getJobType());
        job.setOfferedSalary(jobDto.getOfferedSalary());
        job.setExperience(jobDto.getExperience());
        job.setQualification(jobDto.getQualification());
        job.setCountry(jobDto.getCountry());
        job.setCity(jobDto.getCity());
        job.setEmailAddress(jobDto.getEmailAddress());
        job.setDescription(jobDto.getDescription());
        job.setStartDate(jobDto.getStartDate());
        job.setEndDate(jobDto.getEndDate());


        Job updatedJob = jobRepository.save(job);

        return mapToDto(updatedJob);
    }

    @Override
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(()-> new JobApiException(HttpStatus.BAD_REQUEST, "Job not found"));
        jobRepository.delete(job);

    }

    @Override
    public List<JobDto> getAllJobsByUserId(Long userId) {
        List<Job> jobs = jobRepository.findAllJobsByUserId(userId);
        return jobs.stream().map(job -> mapToDto(job)).collect(Collectors.toList());
    }




    /*
           * Mapping Entity and Dtos *
    */

    private JobDto mapToDto(Job job){
        JobDto jobDto = modelMapper.map(job, JobDto.class);
        return jobDto;
    }

    private Job mapToEntity(JobDto jobDto){
        Job job = modelMapper.map(jobDto, Job.class);
        return job;
    }
}
