package com.example.jobzilla_backend.repositories;

import com.example.jobzilla_backend.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
   List<Candidate> findByJobId(Long jobId);
}
