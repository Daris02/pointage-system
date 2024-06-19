package com.hei.app.pointage;

import java.time.Instant;

import org.junit.jupiter.api.Test;

import com.hei.app.employe.Categorie;
import com.hei.app.employe.CategorieType;
import com.hei.app.employe.Employe;

public class PointageTest {
    @Test
    void simple_test() {
        var normal = new Categorie(CategorieType.normal, 40, 100_000);
        var superieur = new Categorie(CategorieType.cadre_supérieur, 38, 200_000);
        var gardien = new Categorie(CategorieType.gardien, 14-10, 110_000);
        var chauffeur = new Categorie(CategorieType.chauffeur, 8, 80_000);

        var alice = new Employe(
            "Alice", "Doe", "1234", 
            Instant.parse("2000-01-01 00:00:00"), 
            Instant.parse("2023-05-01 00:00:00"),
            Instant.parse("2025-05-01 00:00:00"),
            normal
        );
        var bob = new Employe(
            "Bob", "Doe", "5678",
            Instant.parse("2001-02-02 00:00:00"),
            Instant.parse("2020-07-12 00:00:00"),
            Instant.parse("2030-07-12 00:00:00"),
            gardien
        );
    }
}
