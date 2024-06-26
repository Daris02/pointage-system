package com.hei.app.scoring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.hei.app.calendrier.SpecialCalendar;
import com.hei.app.employe.Category;
import com.hei.app.employe.CategoryType;
import com.hei.app.employe.Employee;

public class ScoringTest {

    @Test
    void valid_salary_working_hour_without_HM() {
        var gardien = new Category(CategoryType.guardian, 70, 100_000);

        var rakoto = new Employee(
            "Rakoto", "N.", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var calandarMay = new SpecialCalendar(5, 2024, List.of());
        var calandarJune = new SpecialCalendar(6, 2024, List.of()); 
        var calandarJuly = new SpecialCalendar(7, 2024, List.of());
        
        List<Attendance> listAttendanceMay = new ArrayList<>();
        for (int i = 26; i <= 31; i++) {
            listAttendanceMay.add(new Attendance(i, "07:00-17:00"));
        }
        
        List<Attendance> listAttendanceJune = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            listAttendanceJune.add(new Attendance(i, "07:00-17:00"));
        }
        
        List<Attendance> listAttendanceJuly = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            listAttendanceJuly.add(new Attendance(i, "07:00-17:00"));
        }

        var scoringRakotoMay = new Scoring(rakoto, calandarMay, listAttendanceMay);
        var scoringRakotoJune = new Scoring(rakoto, calandarJune, listAttendanceJune);
        var scoringRakotoJuly = new Scoring(rakoto, calandarJuly, listAttendanceJuly);

        scoringRakotoMay.calculAfterScoring();
        scoringRakotoJune.calculAfterScoring();
        scoringRakotoJuly.calculAfterScoring();
        
        assertEquals(rounded(420 * 1_428.571), rounded(rakoto.getSalary().getBrute()));
    }

    @Test
    void valid_salary_working_hour_with_HM() {
        var gardien = new Category(CategoryType.guardian, 98, 100_000);

        var rabe = new Employee(
            "Rabe", "N.", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var calandarMay = new SpecialCalendar(5, 2024, List.of());
        var calandarJune = new SpecialCalendar(6, 2024, List.of()); 
        var calandarJuly = new SpecialCalendar(7, 2024, List.of());
        
        List<Attendance> listAttendanceMay = new ArrayList<>();
        for (int i = 26; i <= 31; i++) {
            listAttendanceMay.add(new Attendance(i, "18:00-04:00"));
        }
        
        List<Attendance> listAttendanceJune = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            listAttendanceJune.add(new Attendance(i, "18:00-04:00"));
        }
        
        List<Attendance> listAttendanceJuly = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            listAttendanceJuly.add(new Attendance(i, "18:00-04:00"));
        }

        var scoringRabeMay = new Scoring(rabe, calandarMay, listAttendanceMay);
        var scoringRabeJune = new Scoring(rabe, calandarJune, listAttendanceJune);
        var scoringRabeJuly = new Scoring(rabe, calandarJuly, listAttendanceJuly);

        scoringRabeMay.calculAfterScoring();
        scoringRabeJune.calculAfterScoring();
        scoringRabeJuly.calculAfterScoring();
        
        assertEquals(rounded(588 * 1_326.530) , rounded(rabe.getSalary().getBrute()));
    }

    @Test
    void valid_salary_working_hour_day_with_holiday() {
        var gardien = new Category(CategoryType.guardian, 70, 100_000);

        var rakoto = new Employee(
            "Rakoto", "N.", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var calandarMay = new SpecialCalendar(5, 2024, List.of());
        var calandarJune = new SpecialCalendar(6, 2024, List.of(17, 25, 26)); 
        var calandarJuly = new SpecialCalendar(7, 2024, List.of());
        
        List<Attendance> listAttendanceMay = new ArrayList<>();
        for (int i = 26; i <= 31; i++) {
            listAttendanceMay.add(new Attendance(i, "07:00-17:00"));
        }
        
        List<Attendance> listAttendanceJune = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            listAttendanceJune.add(new Attendance(i, "07:00-17:00"));
        }
        
        List<Attendance> listAttendanceJuly = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            listAttendanceJuly.add(new Attendance(i, "07:00-17:00"));
        }

        var scoringRakotoMay = new Scoring(rakoto, calandarMay, listAttendanceMay);
        var scoringRakotoJune = new Scoring(rakoto, calandarJune, listAttendanceJune);
        var scoringRakotoJuly = new Scoring(rakoto, calandarJuly, listAttendanceJuly);

        scoringRakotoMay.calculAfterScoring();
        scoringRakotoJune.calculAfterScoring();
        scoringRakotoJuly.calculAfterScoring();
        
        /** This test for 30% of HM */
        // assertEquals(rounded(612_856.959), rounded(rakoto.getSalary().getBrute()));

        /** This test for 50% of HM */
        assertEquals(rounded(621_428.385), rounded(rakoto.getSalary().getBrute()));
    }

    @Test
    void valid_salary_working_hour_night_with_holiday() {
        var gardien = new Category(CategoryType.guardian, 98, 100_000);

        var rabe = new Employee(
            "Rabe", "N.", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var calandarMay = new SpecialCalendar(5, 2024, List.of());
        var calandarJune = new SpecialCalendar(6, 2024, List.of(17, 25, 26)); 
        var calandarJuly = new SpecialCalendar(7, 2024, List.of());
        
        List<Attendance> listAttendanceMay = new ArrayList<>();
        for (int i = 26; i <= 31; i++) {
            listAttendanceMay.add(new Attendance(i, "18:00-04:00"));
        }
        
        List<Attendance> listAttendanceJune = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            listAttendanceJune.add(new Attendance(i, "18:00-04:00"));
        }
        
        List<Attendance> listAttendanceJuly = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            listAttendanceJuly.add(new Attendance(i, "18:00-04:00"));
        }

        var scoringRabeMay = new Scoring(rabe, calandarMay, listAttendanceMay);
        var scoringRabeJune = new Scoring(rabe, calandarJune, listAttendanceJune);
        var scoringRabeJuly = new Scoring(rabe, calandarJuly, listAttendanceJuly);

        scoringRabeMay.calculAfterScoring();
        scoringRabeJune.calculAfterScoring();
        scoringRabeJuly.calculAfterScoring();
        
        /** This test for 30% of HM */
        // assertEquals(rounded(796_713.92), rounded(rabe.getSalary().getBrute()));
        
        /** This test for 50% of HM */
        assertEquals(rounded(807_856.77), rounded(rabe.getSalary().getBrute()));
    }

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

        scoring_rakoto.calculAfterScoring();
        assertEquals(rakoto.getSalary().getNet(), 80_000.0);
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

        scoring_rakoto.calculAfterScoring();
        assertTrue(rakoto.getSalary().getNet() > gardien.getSalaryMatche() * 0.8);
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

        scoring_rakoto.calculAfterScoring();
        assertTrue(rakoto.getSalary().getNet() > gardien.getSalaryMatche() * 0.8);
    }

    @Test
    void scoring_with_night_work() {
        var calandar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));

        var gardien = new Category(CategoryType.guardian, 98, 110_000);

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
                new Attendance(18, "18:00-04:00"),
                new Attendance(19, "18:00-04:00"),
                new Attendance(20, "18:00-04:00"),
                new Attendance(21, "18:00-04:00"),
                new Attendance(22, "18:00-04:00"),
                new Attendance(23, "18:00-04:00"),
                new Attendance(24, "18:00-04:00")
            )
        );

        scoring_rabe.calculAfterScoring();
        assertTrue(rabe.getSalary().getBrute() > gardien.getSalaryMatche() * 1.2);
    }

    double rounded(double number) {
        DecimalFormat df = new DecimalFormat("#");
        String numberRoundString = df.format(number);
        double numberRound = Double.parseDouble(numberRoundString);
        return numberRound;
    }
}
