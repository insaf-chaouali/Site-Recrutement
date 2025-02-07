package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Admin extends Utilisateur {
 private int id;
     private long telephone;
    private String login;
}
