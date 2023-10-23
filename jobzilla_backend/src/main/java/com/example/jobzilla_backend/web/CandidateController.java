package com.example.jobzilla_backend.web;

import com.example.jobzilla_backend.dtos.CandidateDto;
import com.example.jobzilla_backend.services.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CandidateController {

    private CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/jobs/{jobId}/candidates")
    public ResponseEntity<CandidateDto>  apply(@RequestBody CandidateDto candidateDto, @PathVariable(name = "jobId") Long jobId){
        return new ResponseEntity<>(candidateService.apply(jobId, candidateDto), HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{jobId}/candidates/{candidateId}")
    public ResponseEntity<CandidateDto> getCandidatesById(@PathVariable(name = "jobId") Long jobId, @PathVariable(name = "candidateId") Long candidateId) {
        return new ResponseEntity<>(candidateService.getCandidatesById(jobId, candidateId), HttpStatus.OK);
    }

    @GetMapping("/jobs/{jobId}/candidates")
    public ResponseEntity<List<CandidateDto>> getCandidateByJobId(@PathVariable(value = "jobId") Long jobId){
        return new ResponseEntity<>(candidateService.getCandidatesByJobId(jobId), HttpStatus.OK);
    }
}
