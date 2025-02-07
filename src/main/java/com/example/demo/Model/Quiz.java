package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String question;
    private int recruteurId;

    @OneToOne(mappedBy = "quiz")
    private Offre offre;
}
