package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Candidate extends Utilisateur {

    @Temporal(TemporalType.DATE)
    private Date datenaissance;

    private String cvUrl;

    private long telephone;

    private String gouvernorat;

    @OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidature> candidatures;
}
