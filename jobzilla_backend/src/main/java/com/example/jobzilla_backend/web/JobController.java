package com.example.jobzilla_backend.web;

import com.example.jobzilla_backend.dtos.JobDto;
import com.example.jobzilla_backend.dtos.JobResponse;
import com.example.jobzilla_backend.services.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/users/{userId}/add_job")
    public ResponseEntity<JobDto> saveJob(@RequestBody JobDto jobDto, @PathVariable(name = "userId") Long userId){
        return new ResponseEntity<>(jobService.saveJob(jobDto, userId), HttpStatus.CREATED);
    }

//    @GetMapping("/jobs")
//    public ResponseEntity<List<JobDto>> getAllJobs(){
//
//        return new ResponseEntity<>(jobService.getAllJobs(), HttpStatus.OK);
//    }
    @GetMapping("/jobs")
    public JobResponse getAllJobs(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "6", required = false) int pageSize,
                                  @RequestParam(required = false) String keyword){

        return jobService.getAllPosts(pageNo, pageSize, keyword);
    }


    @GetMapping("/jobs/{jobId}")
    public ResponseEntity<JobDto> getJobById(@PathVariable(name = "jobId") Long jobId){
        return new ResponseEntity<>(jobService.getJobById(jobId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/jobs")
    public ResponseEntity<List<JobDto>> getJobsByUserId(@PathVariable(name = "userId") Long userId){
        return new ResponseEntity<>(jobService.getAllJobsByUserId(userId), HttpStatus.OK);
    }

    @PutMapping("/users/{userId}/jobs/{id}")
    public ResponseEntity<JobDto> updateJob(@RequestBody JobDto jobDto, @PathVariable(name = "id") Long id){
        return new ResponseEntity<>(jobService.updateJob(jobDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable(name = "id") Long id){
        jobService.deleteJob(id);
        return new ResponseEntity<>("job deleted successfully", HttpStatus.OK);
    }


}
