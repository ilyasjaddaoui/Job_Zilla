package com.example.jobzilla_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(length = 65555)
    private String description;
    private Date startDate;
    private Date endDate;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private Set<Candidate> candidates = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
