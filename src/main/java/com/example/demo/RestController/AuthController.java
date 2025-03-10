package com.example.demo.RestController;
import com.example.demo.Jwt.JwtResponse;
import com.example.demo.Jwt.JwtUtils;
import com.example.demo.Model.ERole;

import com.example.demo.Model.RefreshToken;
import com.example.demo.Model.Role;
import com.example.demo.Model.Utilisateur;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UtilisateurRepository;
import com.example.demo.Request.LoginRequest;
import com.example.demo.Request.MessageResponse;
import com.example.demo.Request.SignupRequest;
import com.example.demo.Service.RefreshTokenService;
import com.example.demo.Service.UserDetailsImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping(value = "/signup", produces = "application/json")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (utilisateurRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Username is already in use"));
        }
        if (utilisateurRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use"));
        }

        Utilisateur utilisateur = new Utilisateur(signupRequest.getUsername(), signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Role userRole = roleRepository.findByName(ERole.ROLE_CANDIDATE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.toUpperCase()) { // Normalize to upper case
                    case "ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    case "RECRUTEUR":
                        Role recruiterRole = roleRepository.findByName(ERole.ROLE_RECRUITER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(recruiterRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_CANDIDATE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);
                }
            });
        }

        utilisateur.setRoles(roles);
        utilisateurRepository.save(utilisateur);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

    @PostMapping(value = "/signin", produces = "application/json")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);

        List<String> roles = userDetails.getAuthorities()
                .stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        String redirectUrl = determineRedirectUrl(roles);

        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(),
                userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), roles, redirectUrl));
    }

    private String determineRedirectUrl(List<String> roles) {
        if (roles.contains(ERole.ROLE_ADMIN.name())) {
            return "/HomeAdmin";
        } else if (roles.contains(ERole.ROLE_RECRUITER.name())) {
            return "/homeRecruiter";
        } else if (roles.contains(ERole.ROLE_CANDIDATE.name())) {
            return "/homeCandidate";
        } else {
            return "/home";  // Default redirection if no role is matched
        }
    }
}