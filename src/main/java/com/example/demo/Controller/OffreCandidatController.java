package com.example.demo.Controller;

import com.example.demo.Model.Offre;
import com.example.demo.Service.CandidatureService;
import com.example.demo.Service.OffreService;
import com.example.demo.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@Controller
@RequestMapping("/candidats/offres")
public class OffreCandidatController {

    @Autowired
    private OffreService offreService;

    @Autowired
    private CandidatureService candidatureService;

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/all")
    public String afficherOffresPourCandidats(Model model) {
        model.addAttribute("offres", offreService.getAllOffres());
        return "candidat-offres";
    }

    @GetMapping("/details/{id}")
    public String afficherDetailsOffre(@PathVariable int id, Model model) {
        Offre offre = offreService.getOffreById(id);
        if (offre == null) {
            model.addAttribute("errorMessage", "Offre introuvable.");
            return "error-page";  // Page d'erreur si l'offre n'existe pas
        }
        model.addAttribute("offre", offre);
        return "candidat-details";
    }

    @PostMapping("/postuler/{id}")
    public String postulerOffre(
            @RequestParam("cvCandidat") MultipartFile cvCandidat,
            @PathVariable int id,
            Model model) {
        return "mes-candidatures";
    }
}