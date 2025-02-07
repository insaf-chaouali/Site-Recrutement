package com.example.demo.Controller;

import com.example.demo.Model.Candidate;
import com.example.demo.Model.Offre;
import com.example.demo.Service.CandidateService;
import com.example.demo.Service.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/candidats")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private OffreService offreService; // Injection du service OffreService

    @GetMapping("/lister")
    public String listerTousLesCandidats(Model model) {
        // Récupérer tous les candidats
        Iterable<Candidate> candidats = candidateService.getAllCandidates();
        // Ajouter les candidats au modèle
        model.addAttribute("candidats", candidats);
        return "lister"; // Vue pour afficher tous les candidats
    }

    // Afficher le profil d'un candidat
    @GetMapping("/profil/{id}")
    public String afficherProfil(@PathVariable int id, Model model) {
        Candidate candidate = candidateService.getCandidateById(id);
        if (candidate != null) {
            model.addAttribute("candidate", candidate);
            return "profil"; // Vue pour afficher le profil du candidat
        } else {
            model.addAttribute("message", "Candidat introuvable.");
            return "error"; // Vue pour l'erreur si le candidat n'est pas trouvé
        }
    }

    // Modifier les informations du profil d'un candidat
    @GetMapping("/modifier/{id}")
    public String modifierProfilForm(@PathVariable int id, Model model) {
        Candidate candidate = candidateService.getCandidateById(id);
        if (candidate != null) {
            model.addAttribute("candidate", candidate);
            return "candidats/modifier-profil"; // Vue pour éditer le profil
        } else {
            model.addAttribute("message", "Candidat introuvable.");
            return "error"; // Vue pour l'erreur
        }
    }

    // Sauvegarder les modifications du profil d'un candidat
    @PostMapping("/modifier")
    public String modifierProfil(@ModelAttribute Candidate candidate, Model model) {
        candidateService.saveCandidate(candidate); // Sauvegarder les modifications
        model.addAttribute("message", "Profil mis à jour avec succès.");
        return "profil" + candidate.getId(); // Rediriger vers le profil mis à jour
    }

    // Afficher le formulaire de création d'un candidat
    @GetMapping("/afficher")
    public String afficherFormulaireCreation(Model model) {
        model.addAttribute("candidate", new Candidate()); // Initialiser un nouvel objet Candidate pour le formulaire
        return "creer-candidat"; // Vue pour afficher le formulaire de création
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // Sauvegarder un nouveau candidat
    @PostMapping("/creer")
    public String creerCandidat(@ModelAttribute Candidate candidate, Model model) {
        candidateService.saveCandidate(candidate); // Sauvegarder le candidat dans la base de données
        model.addAttribute("message", "Candidat créé avec succès.");
        return "profil" + candidate.getId();
    }

    @GetMapping("/home")
    public String afficherPageAccueil(Model model) {
        // Récupérer toutes les offres en utilisant l'instance de OffreService
        Iterable<Offre> offres = offreService.getAllOffres();
        model.addAttribute("offres", offres);
        model.addAttribute("nom", "Alex"); // Exemple de nom, remplacez par la logique appropriée
        return "home-candidate"; // Vue pour afficher la page d'accueil
    }
}