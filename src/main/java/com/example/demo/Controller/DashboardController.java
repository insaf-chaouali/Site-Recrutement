package com.example.demo.Controller;

import com.example.demo.Repository.CandidateRepository;
import com.example.demo.Repository.OffreRepository;
import com.example.demo.Repository.RecruiterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final OffreRepository offreRepository;
    private final CandidateRepository candidateRepository;
    private final RecruiterRepository recruiterRepository;

    // Injection des dépendances via le constructeur
    public DashboardController(OffreRepository offreRepository, CandidateRepository candidateRepository, RecruiterRepository recruiterRepository) {
        this.offreRepository = offreRepository;
        this.candidateRepository = candidateRepository;
        this.recruiterRepository = recruiterRepository;
    }

    // Gérer la page du Dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        long totalOffres = offreRepository.count();
        long totalCandidates = candidateRepository.count();
        long totalRecruiters = recruiterRepository.count();

        model.addAttribute("totalOffres", totalOffres);
        model.addAttribute("totalCandidates", totalCandidates);
        model.addAttribute("totalRecruiters", totalRecruiters);

        return "dashboard";  // Affiche dashboard.html
    }
}
