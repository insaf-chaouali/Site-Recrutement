package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING) // Store the enum as a string in the database
    @Column(length = 20, unique = true) // Ensure uniqueness if applicable
    private ERole name;

    // Default constructor
    public Role() {
    }

    // Optional: Constructor with parameters for convenience
    public Role(ERole name) {
        this.name = name;
    }
}