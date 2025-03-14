package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity(name="refreshtoken")
public class RefreshToken {
        @Id
        @GeneratedValue(strategy = GenerationType. AUTO)
        private long id;
        @OneToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private Utilisateur utilisateur;
        @Column (nullable = false, unique = true)
        private String token;

        @Column (nullable = false)
        private Instant expiryDate;

    }
