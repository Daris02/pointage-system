package com.hei.app.employe;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Categorie {
    private CategorieType nom;
    private int heureDeTravail;
    private double salaireCorrespond;

    public double getIndemnite() {
        return 0.0;
    }
}
