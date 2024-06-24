package com.hei.app.scoring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.hei.app.calendrier.SpecialCalendar;
import com.hei.app.employe.Category;
import com.hei.app.employe.CategoryType;
import com.hei.app.employe.Employee;

public class ScoringTest {
    @Test
    void scoring_without_overtime_work() {
        var calandar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));

        var gardien = new Category(CategoryType.guardian, 70, 100_000);

        var rakoto = new Employee(
            "Rakoto", "Be", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var scoring_rakoto = new Scoring(
            rakoto,
            calandar_juin,
            List.of(
                new Attendance(18, "07:00-17:00"),
                new Attendance(19, "07:00-17:00"),
                new Attendance(20, "07:00-17:00"),
                new Attendance(21, "07:00-17:00"),
                new Attendance(22, "07:00-17:00"),
                new Attendance(23, "07:00-17:00"),
                new Attendance(24, "07:00-17:00")
            )
        );

        assertEquals(scoring_rakoto.calculAfterScoring().getSalary().getNet(), gardien.getSalaryMatche() * 0.8);
    }

    @Test
    void scoring_with_overtime_work() {
        var calandar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));

        var gardien = new Category(CategoryType.guardian, 70, 110_000);

        var rakoto = new Employee(
            "Rakoto", "Be", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var scoring_rakoto = new Scoring(
            rakoto,
            calandar_juin,
            List.of(
                new Attendance(18, "07:00-17:00"),
                new Attendance(19, "07:00-17:00"),
                new Attendance(20, "07:00-17:00"),
                new Attendance(21, "07:00-17:00"),
                new Attendance(22, "07:00-17:00"),
                new Attendance(23, "07:00-17:00"),
                new Attendance(24, "07:00-18:00")
            )
        );

        assertTrue(scoring_rakoto.calculAfterScoring().getSalary().getNet() > gardien.getSalaryMatche() * 0.8);
    }


    @Test
    void scoring_with_holiday_work() {
        var calandar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));

        var gardien = new Category(CategoryType.guardian, 70, 110_000);

        var rakoto = new Employee(
            "Rakoto", "Be", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var scoring_rakoto = new Scoring(
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

        assertTrue(scoring_rakoto.calculAfterScoring().getSalary().getNet() > gardien.getSalaryMatche() * 0.8);
    }

    @Test
    void scoring_with_night_work() {
        var calandar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));

        var gardien = new Category(CategoryType.guardian, 74, 110_000);

        var rabe = new Employee(
            "Rabe", "Be", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var scoring_rabe = new Scoring(
            rabe,
            calandar_juin,
            List.of(
                new Attendance(18, "18:00-05:00"),
                new Attendance(19, "18:00-05:00"),
                new Attendance(20, "18:00-05:00"),
                new Attendance(21, "18:00-05:00"),
                new Attendance(22, "18:00-05:00"),
                new Attendance(23, "18:00-05:00"),
                new Attendance(24, "18:00-05:00")
            )
        );

        assertTrue(scoring_rabe.calculAfterScoring().getSalary().getNet() > (gardien.getSalaryMatche() * 2) * 0.8);
    }
}
