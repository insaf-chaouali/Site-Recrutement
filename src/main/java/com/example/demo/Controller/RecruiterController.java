package com.example.demo.Controller;

import com.example.demo.Model.Recruiter;
import com.example.demo.Service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recruteurs")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;
    @GetMapping("/home")
    public String home(Model model) {
        // Exemple de données pour la page (offres, candidats, etc.)
        model.addAttribute("message", "Bienvenue sur la page d'accueil du recruteur!");
        return "home";
    }
    // Afficher le profil d'un recruteur
    @GetMapping("/profil/{id}")
    public String afficherProfil(@PathVariable int id, Model model) {
        Recruiter recruiter = recruiterService.getRecruiterById(id);
        if (recruiter != null) {
            model.addAttribute("recruiter", recruiter);
            return "profil-recruiter"; // Vue pour afficher le profil du recruteur
        } else {
            model.addAttribute("message", "Recruteur introuvable.");
            return "error";
        }
    }

    // Modifier les informations du profil d'un recruteur
    @GetMapping("/modifier/{id}")
    public String modifierProfilForm(@PathVariable int id, Model model) {
        Recruiter recruiter = recruiterService.getRecruiterById(id);
        if (recruiter != null) {
            model.addAttribute("recruiter", recruiter);
            return "modifier-profil"; // Vue pour modifier le profil
        } else {
            model.addAttribute("message", "Recruteur introuvable.");
            return "error";
        }
    }

    // Sauvegarder les modifications du profil d'un recruteur
    @PostMapping("/modifier")
    public String modifierProfil(@ModelAttribute Recruiter recruiter, Model model) {
        recruiterService.saveRecruiter(recruiter);
        model.addAttribute("message", "Profil mis à jour avec succès.");
        return "redirect:/recruteurs/profil/" + recruiter.getId();
    }

    // Afficher le formulaire de création d'un recruteur
    @GetMapping("/afficher")
    public String afficherFormulaireCreation(Model model) {
        model.addAttribute("recruiter", new Recruiter());
        return "creer-recruteur"; // Vue pour afficher le formulaire de création
    }

    // Sauvegarder un nouveau recruteur
    @PostMapping("/creer")
    public String creerRecruteur(@ModelAttribute Recruiter recruiter, Model model) {
        recruiterService.saveRecruiter(recruiter);
        model.addAttribute("message", "Recruteur créé avec succès.");
        return "redirect:/recruteurs/profil/" + recruiter.getId();
    }
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
}
