package com.example.demo.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private Set<String> roles;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public SignupRequest() {
        // Initialize with default values if necessary
        this.username = "";
        this.email = "";
        this.password = "";
        this.roles = null; // or new HashSet<>() if you want to initialize it as an empty set
    }

    // Constructor with parameters
    public SignupRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}



