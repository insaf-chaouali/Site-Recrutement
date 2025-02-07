package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "offre_id", nullable = false)
    private Offre offre;

    @ManyToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id", nullable = false)
    private Candidate candidat;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datePostulation;

    private String cvPath;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Etat etat = Etat.EN_COURS;

    public Candidature() {
        this.datePostulation = new Date();
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
}