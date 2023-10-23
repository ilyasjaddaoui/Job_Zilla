package com.example.jobzilla_backend.repositories;

import com.example.jobzilla_backend.entities.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;
@EnableJpaRepositories
public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findAllJobsByUserId(Long userId);
    Page<Job> findByJobTitleContaining(String keyword, Pageable pageable);

}
