package com.example.jobzilla_backend.services;

import com.example.jobzilla_backend.dtos.JobDto;
import com.example.jobzilla_backend.dtos.JobResponse;

import java.util.List;

public interface JobService {
    JobDto saveJob(JobDto jobDto, Long userId);
//    List<JobDto> getAllJobs();
    JobResponse getAllPosts(int pageNo, int pageSize, String keyword);
    JobDto getJobById(Long jobId);
    JobDto updateJob(JobDto jobDto, Long id);
    void deleteJob(Long id);

    List<JobDto> getAllJobsByUserId(Long userId);
}
