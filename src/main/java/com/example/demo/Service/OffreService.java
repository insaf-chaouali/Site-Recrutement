package com.example.demo.Service;

import com.example.demo.Model.Candidature;
import com.example.demo.Model.Offre;
import com.example.demo.Repository.CandidatureRepository;
import com.example.demo.Repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class OffreService {

    @Autowired
    private OffreRepository offreRepository;
    @Autowired
    private CandidatureRepository candidatureRepository;

    // Méthode pour enregistrer l'offre dans la base de données
    public void saveOffre(Offre offre) {
        offreRepository.save(offre); // Sauvegarde dans la base
    }

    // Méthode pour obtenir toutes les offres
    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    // Méthode pour obtenir une offre par son ID
    public Offre getOffreById(int id) {
        return offreRepository.findById(id).orElse(null);
    }
    public Offre updateOffre(Offre offre) {
        return offreRepository.saveAndFlush(offre);
    }

    // Méthode pour supprimer une offre par son ID
    public void deleteOffre(int id) {
        offreRepository.deleteById(id);
    }

    // Enregistrer une candidature
    public void postulerCandidature(Candidature candidature, int offreId) {
        // Récupérer l'offre et associer la candidature
        Offre offre = getOffreById(offreId);
        if (offre != null) {
            candidature.setOffre(offre); // Associer l'offre à la candidature
            candidatureRepository.save(candidature); // Sauvegarder la candidature
        }
    }


}