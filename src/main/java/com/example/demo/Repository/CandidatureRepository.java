package com.example.demo.Repository;

import com.example.demo.Model.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Integer> {
   // @Query("SELECT c FROM Candidature c JOIN FETCH c.offre WHERE c.candidat.id = :candidateId")
   List<Candidature> findByCandidatId(@Param("candidateId") int candidateId);
   // Recherche par nom du candidat contenant une chaîne spécifique
   List<Candidature> findByCandidatNomContaining(String nom);

   // Recherche par état de la candidature
   List<Candidature> findByEtat(String etat);

   // Recherche par nom du candidat et état de la candidature
   List<Candidature> findByCandidatNomContainingAndEtat(String nom, String etat);
}
