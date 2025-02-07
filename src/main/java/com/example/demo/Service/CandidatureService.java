package com.example.demo.Service;

import com.example.demo.Model.Candidature;
import com.example.demo.Repository.CandidatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatureService {

    @Autowired
    private CandidatureRepository candidatureRepository;

    // Récupérer toutes les candidatures
    public List<Candidature> getAllCandidatures() {
        return candidatureRepository.findAll();
    }

    // Récupérer une candidature par son ID
    public Candidature getCandidatureById(int id) {
        Optional<Candidature> candidature = candidatureRepository.findById(id);
        return candidature.orElse(null);  // Retourne null si la candidature n'existe pas
    }

    // Filtrer les candidatures selon les critères (nom du candidat et état)
    public List<Candidature> filterCandidatures(String keyword, String etat) {
        if (keyword != null && etat != null) {
            return candidatureRepository.findByCandidatNomContainingAndEtat(keyword, etat);
        } else if (keyword != null) {
            return candidatureRepository.findByCandidatNomContaining(keyword);
        } else if (etat != null) {
            return candidatureRepository.findByEtat(etat);
        } else {
            return candidatureRepository.findAll();  // Retourne toutes les candidatures si aucun filtre
        }
    }

    // Sauvegarder ou mettre à jour une candidature
    public void saveCandidature(Candidature candidature) {
        candidatureRepository.save(candidature);
    }

    // Récupérer les candidatures d'un candidat spécifique par son ID
    public List<Candidature> getCandidaturesByCandidateId(int candidateId) {
        return candidatureRepository.findByCandidatId(candidateId);
    }

    // Méthode pour supprimer une candidature par ID
    public void deleteCandidatureById(int id) {
        candidatureRepository.deleteById(id); // Supprimer la candidature dans la base de données
    }
}
