package com.example.demo.Controller;

import com.example.demo.Model.Candidature;
import com.example.demo.Model.Offre;
import com.example.demo.Service.CandidatureService;
import com.example.demo.Service.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/offres")
public class OffreController {

    @Autowired
    private OffreService offreService;

    @Autowired
    private CandidatureService candidatureService; // Add this line

    @GetMapping("/all")
    public String afficherOffres(Model model) {
        List<Offre> offres = offreService.getAllOffres();
        model.addAttribute("offres", offres);
        return "offres";
    }

    @GetMapping("/new")
    public String ajouterOffreForm(Model model) {
        model.addAttribute("offre", new Offre());
        return "offre-form";
    }

    @PostMapping("/save")
    public String saveOffre(@ModelAttribute Offre offre) {
        offreService.saveOffre(offre);
        return "redirect:/offres/all";
    }

    @GetMapping("/delete/{id}")
    public String supprimerOffre(@PathVariable int id) {
        offreService.deleteOffre(id);
        return "redirect:/offres/all";
    }

    @GetMapping("/edit/{id}")
    public String modifierOffreForm(@PathVariable int id, Model model) {
        Offre offre = offreService.getOffreById(id);
        model.addAttribute("offre", offre);
        return "update-offre";
    }

    @PostMapping("/update/{id}")
    public String updateOffre(@PathVariable("id") int id, @ModelAttribute Offre offre) {
        offre.setId(id); // Ensure the ID is set for the update
        offreService.updateOffre(offre);
        return "redirect:/offres/all";
    }


}