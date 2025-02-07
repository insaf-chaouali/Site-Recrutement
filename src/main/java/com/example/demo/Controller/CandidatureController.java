package com.example.demo.Controller;

import com.example.demo.Model.Candidature;
import com.example.demo.Service.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/candidatures")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    @GetMapping("/mes")
    public String afficherMesCandidatures(@RequestParam("candidateId") int candidateId, Model model) {
        // Récupère les candidatures liées au candidateId
        List<Candidature> candidatures = candidatureService.getCandidaturesByCandidateId(candidateId);


        candidatures.forEach(c -> {
            System.out.println("Candidature ID: " + c.getId());
            System.out.println("Offre ID: " + c.getOffre().getId());
        });

        model.addAttribute("candidatures", candidatures);
        return "mes-candidatures"; // Retourne le nom de la vue Thymeleaf
    }


}