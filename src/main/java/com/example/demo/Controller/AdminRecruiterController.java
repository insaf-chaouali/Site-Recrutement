package com.example.demo.Controller;

import com.example.demo.Service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/recruiters")
public class AdminRecruiterController {

    @Autowired
    private RecruiterService recruiterService;



    // Liste des recruteurs
    @GetMapping
    public String listRecruiters(Model model) {
        model.addAttribute("recruiters", recruiterService.getAllRecruiters());
        return "recruiter-management";  // This will render recruiter-management.html
    }




    // Supprimer un recruteur
    @GetMapping("/delete/{id}")
    public String deleteRecruiter(@PathVariable int id) {
        recruiterService.deleteRecruiter(id);
        return "redirect:/admin/recruiters";  // After deleting, redirect back to recruiter list
    }
}

