package com.example.demo.Repository;

import com.example.demo.Model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Integer> {
    boolean existsByEmail(String email);
}
