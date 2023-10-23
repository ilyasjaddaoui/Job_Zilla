package com.example.jobzilla_backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CandidateDto {
    private Long id;
    private String message;
    private String resume;

}
