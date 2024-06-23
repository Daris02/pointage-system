package com.hei.app;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import com.hei.app.calendrier.SpecialCalendar;
import com.hei.app.employe.Category;
import com.hei.app.employe.CategoryType;
import com.hei.app.employe.Employee;
import com.hei.app.scoring.Attendance;
import com.hei.app.scoring.Scoring;

public class Main {

    public static void main(String[] args) {
        SpecialCalendar calandar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));

        Category gardien = new Category(CategoryType.guardian, 56, 110_000);

        Employee rakoto = new Employee(
            "Rakoto", "Name", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        Scoring scoring_rakoto = new Scoring(
            rakoto,
            calandar_juin,
            List.of(
                new Attendance(17, "07:00-17:00"),
                new Attendance(18, "07:00-17:00"),
                new Attendance(19, "07:00-17:00"),
                new Attendance(20, "07:00-17:00"),
                new Attendance(21, "07:00-17:00"),
                new Attendance(22, "07:00-17:00"),
                new Attendance(23, "07:00-17:00")
            )
        );

        System.out.println(scoring_rakoto.calculAfterScoring().getSalary());
    }
    
    // public static void main(String[] args) {
    //     String intervalleStr = "06:00-18:00";

    //     // Découper la chaîne en deux parties
    //     String[] parties = intervalleStr.split("-");
    //     if (parties.length != 2) {
    //         throw new IllegalArgumentException("Format d'intervalle invalide: " + intervalleStr);
    //     }

    //     // Convertir les parties en objets LocalTime
    //     LocalTime debut = LocalTime.parse(parties[0], DateTimeFormatter.ofPattern("HH:mm"));
    //     LocalTime fin = LocalTime.parse(parties[1], DateTimeFormatter.ofPattern("HH:mm"));

    //     // Calculer la durée en secondes
    //     Duration duree = Duration.between(debut, fin);
    //     long dureeSeconde = duree.toSeconds();

    //     System.out.println("Durée de l'intervalle (secondes): " + dureeSeconde/60/60);
    // }
    
    // public static void main(String[] args) {
    //     System.out.println("Nom du jour de la semaine : " 
    //     + LocalDate.of(2024, 6, 22).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
    // }
}
