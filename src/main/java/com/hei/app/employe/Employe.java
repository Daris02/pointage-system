package com.hei.app.employe;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employe {
    private String nom;
    private String prenom;
    private String matricule;
    private Instant dateNaissance;
    private Instant dateEmbauche;
    private Instant finContrat;
    private Categorie categorie;

    public double getSalaireNet() {
        double salaireBrute = categorie.getSalaireCorrespond();
        return salaireBrute * 0.8;
    }
}
