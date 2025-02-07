package com.example.demo.Repository;

import com.example.demo.Model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

    // Trouver un candidat par son email
    Optional<Candidate> findByEmail(String email);
}
