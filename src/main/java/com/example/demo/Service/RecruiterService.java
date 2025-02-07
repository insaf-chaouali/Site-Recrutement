package com.example.demo.Service;

import com.example.demo.Model.Recruiter;
import com.example.demo.Repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    // Récupérer un recruteur par son ID
    public Recruiter getRecruiterById(int id) {
        Optional<Recruiter> recruiter = recruiterRepository.findById(id);
        return recruiter.orElse(null); // Retourne null si le recruteur n'existe pas
    }

    // Sauvegarder ou mettre à jour un recruteur
    public void saveRecruiter(Recruiter recruiter) {
        recruiterRepository.save(recruiter);
    }

    // Récupérer tous les recruteurs
    public List<Recruiter> getAllRecruiters() {
        return recruiterRepository.findAll();
    }

    // Supprimer un recruteur par son ID
    public void deleteRecruiter(int id) {
        recruiterRepository.deleteById(id);
    }
}
