package com.example.jobzilla_backend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class JobResponse {
    private List<JobDto> content;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean last;
}
