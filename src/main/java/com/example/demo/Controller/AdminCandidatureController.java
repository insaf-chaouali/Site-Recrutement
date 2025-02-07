package com.example.demo.Controller;

import com.example.demo.Model.Candidature;
import com.example.demo.Service.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequestMapping("/admin/candidatures")
public class AdminCandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    // Affichage de toutes les candidatures avec option de filtrage
    @GetMapping
    public String afficherCandidatures(@RequestParam(value = "keyword", required = false) String keyword,
                                       @RequestParam(value = "etat", required = false) String etat,
                                       Model model) {
        List<Candidature> candidatures = candidatureService.filterCandidatures(keyword, etat);

        // Ajouter les candidatures filtrées au modèle
        model.addAttribute("candidatures", candidatures);
        model.addAttribute("keyword", keyword);
        model.addAttribute("etat", etat);

        return "admin-candidatures"; // La vue affichée (admin-candidatures.html)
    }

    // Méthode pour supprimer une candidature
    @GetMapping("/delete/{id}")
    public String supprimerCandidature(@PathVariable("id") int id) {
        candidatureService.deleteCandidatureById(id); // Appel au service pour supprimer la candidature
        return "redirect:/admin/candidatures"; // Redirige vers la liste des candidatures après suppression
    }
}
