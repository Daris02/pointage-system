package com.hei.app.scoring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.hei.app.calendrier.SpecialCalendar;
import com.hei.app.employe.Category;
import com.hei.app.employe.CategoryType;
import com.hei.app.employe.Employee;

public class ScoringTest {
    @Test
    void simple_test() {
        var calandar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));

        var gardien = new Category(CategoryType.guardian, 24, 100_000);

        var rakoto = new Employee(
            "Rakoto", "Be", "1234",
            Instant.parse("2001-02-02T00:00:00Z"),
            Instant.parse("2020-07-12T00:00:00Z"),
            Instant.parse("2030-07-12T00:00:00Z"),
            gardien
        );

        var scoring_rakoto = new Scoring(
            rakoto,
            calandar_juin,
            List.of(
                new Attendance(17, "08:00-17:00"),
                new Attendance(18, "08:00-17:00"),
                new Attendance(19, "08:00-17:00"),
                new Attendance(20, "08:00-17:00"),
                new Attendance(21, "08:00-17:00"),
                new Attendance(22, "08:00-17:00"),
                new Attendance(23, "08:00-17:00")
                // new Attendance(24, "08:00-17:00")
            )
        );

        assertEquals(390_000, scoring_rakoto.calculAfterScoring().getSalary().getNet());
    }
}
