package com.example.jobzilla_backend.services;

import com.example.jobzilla_backend.dtos.CandidateDto;

import java.util.List;

public interface CandidateService {
    CandidateDto apply(Long jobId, CandidateDto candidateDto);
    CandidateDto getCandidatesById(Long jobId, Long candidateId);
    List<CandidateDto> getCandidatesByJobId(Long jobId);


}
