package com.example.jobzilla_backend.dtos;


import com.example.jobzilla_backend.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class JobDto {
    private Long id;
    private String companyName;
    private String companyLogo;
    private String jobTitle;
    private String jobCategory;
    private String jobType;
    private String offeredSalary;
    private String experience;
    private String qualification;
    private String country;
    private String city;
    private String emailAddress;
    private String description;
    private Date startDate;
    private Date endDate;
    @JsonIgnore
    private Set<CandidateDto> candidates;
    @JsonIgnore
    private UserDto user;
}
