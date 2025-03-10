package com.example.demo.Controller;

import com.example.demo.Model.Utilisateur;
import com.example.demo.Repository.UtilisateurRepository;
import com.example.demo.Request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthentificationController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Show home page
    @GetMapping("/home")
    public String showHomePage() {
        return "HomePrincipal/index";
    }

    // Show sign-up form
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("signupRequest", new SignupRequest());
        return "connexion/signup";
    }


    @PostMapping("/signup1")
    public String registerUser(SignupRequest signupRequest, Model model) {

        if (utilisateurRepository.existsByUsername(signupRequest.getUsername())) {
            model.addAttribute("error", "Username is already in use");
            return "connexion/signup";
        }
        // Check if email already exists
        if (utilisateurRepository.existsByEmail(signupRequest.getEmail())) {
            model.addAttribute("error", "Email is already in use");
            return "connexion/signup";
        }


        Utilisateur utilisateur = new Utilisateur(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                signupRequest.getPassword()
        );

        utilisateurRepository.save(utilisateur);
        return "redirect:/auth/home";
    }

    // Show sign-in form
    @GetMapping("/signin")
    public String showSigninForm() {

        return "connexion/signin";
    }


    @PostMapping("/signin")
    public String authenticateUser(String username, String password, Model model) {

        boolean isAuthenticated = false;
        if (!isAuthenticated) {
            model.addAttribute("error", "Invalid username or password");
            return "connexion/signin";
        }

        return "redirect:/auth/home";
    }
}