package com.example.demo.Service;

import com.example.demo.Model.Candidate;
import com.example.demo.Repository.CandidateRepository;
import com.example.demo.Repository.CandidatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidatureRepository candidatureRepository;

    // Method to get a candidate by their email
    public Candidate getCandidateByEmail(String emailCandidat) {
        Optional<Candidate> candidate = candidateRepository.findByEmail(emailCandidat);
        return candidate.orElse(null); // Returns null if no candidate is found
    }

    // Method to get a candidate by their ID
    public Candidate getCandidateById(int candidateId) {
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        return candidate.orElse(null); // Returns null if no candidate is found
    }

    // Method to get all candidates
    public Iterable<Candidate> getAllCandidates() {
        return candidateRepository.findAll(); // Finds all candidates
    }

    // Method to save or update a candidate
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate); // Saves or updates the candidate
    }

    // Method to delete a candidate by ID
    public void deleteCandidate(int candidateId) {
        candidateRepository.deleteById(candidateId); // Deletes the candidate by their ID
    }
    
}