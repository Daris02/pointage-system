package com.hei.app.pointing;

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

public class PointingTest {

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

        var pointingRakotoMay = new Pointing(rakoto, calandarMay, listAttendanceMay);
        var pointingRakotoJune = new Pointing(rakoto, calandarJune, listAttendanceJune);
        var pointingRakotoJuly = new Pointing(rakoto, calandarJuly, listAttendanceJuly);

        pointingRakotoMay.calculAfterPointing();
        pointingRakotoJune.calculAfterPointing();
        pointingRakotoJuly.calculAfterPointing();
        
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
            listAttendanceMay.add(new Attendance(i, "17:00-07:00"));
        }
        
        List<Attendance> listAttendanceJune = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            listAttendanceJune.add(new Attendance(i, "17:00-07:00"));
        }
        
        List<Attendance> listAttendanceJuly = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            listAttendanceJuly.add(new Attendance(i, "17:00-07:00"));
        }

        var pointingRabeMay = new Pointing(rabe, calandarMay, listAttendanceMay);
        var pointingRabeJune = new Pointing(rabe, calandarJune, listAttendanceJune);
        var pointingRabeJuly = new Pointing(rabe, calandarJuly, listAttendanceJuly);

        pointingRabeMay.calculAfterPointing();
        pointingRabeJune.calculAfterPointing();
        pointingRabeJuly.calculAfterPointing();
        
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

        var pointingRakotoMay = new Pointing(rakoto, calandarMay, listAttendanceMay);
        var pointingRakotoJune = new Pointing(rakoto, calandarJune, listAttendanceJune);
        var pointingRakotoJuly = new Pointing(rakoto, calandarJuly, listAttendanceJuly);

        pointingRakotoMay.calculAfterPointing();
        pointingRakotoJune.calculAfterPointing();
        pointingRakotoJuly.calculAfterPointing();
        
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
            listAttendanceMay.add(new Attendance(i, "17:00-07:00"));
        }
        
        List<Attendance> listAttendanceJune = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            listAttendanceJune.add(new Attendance(i, "17:00-07:00"));
        }
        
        List<Attendance> listAttendanceJuly = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            listAttendanceJuly.add(new Attendance(i, "17:00-07:00"));
        }

        var pointingRabeMay = new Pointing(rabe, calandarMay, listAttendanceMay);
        var pointingRabeJune = new Pointing(rabe, calandarJune, listAttendanceJune);
        var pointingRabeJuly = new Pointing(rabe, calandarJuly, listAttendanceJuly);

        pointingRabeMay.calculAfterPointing();
        pointingRabeJune.calculAfterPointing();
        pointingRabeJuly.calculAfterPointing();
        
        /** This test for 30% of HM */
        // assertEquals(rounded(796_713.92), rounded(rabe.getSalary().getBrute()));
        
        /** This test for 50% of HM */
        assertEquals(rounded(807_856.77), rounded(rabe.getSalary().getBrute()));
    }

    @Test
    void pointing_without_overtime_work() {
        var calandar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));

        var gardien = new Category(CategoryType.guardian, 70, 100_000);

        var rakoto = new Employee(
            "Rakoto", "Be", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var pointing_rakoto = new Pointing(
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

        pointing_rakoto.calculAfterPointing();
        assertEquals(rakoto.getSalary().getNet(), 80_000.0);
    }

    @Test
    void pointing_with_overtime_work() {
        var calandar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));

        var gardien = new Category(CategoryType.guardian, 70, 110_000);

        var rakoto = new Employee(
            "Rakoto", "Be", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var pointing_rakoto = new Pointing(
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

        pointing_rakoto.calculAfterPointing();
        assertTrue(rakoto.getSalary().getNet() > gardien.getSalaryMatche() * 0.8);
    }

    @Test
    void pointing_with_holiday_work() {
        var calandar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));

        var gardien = new Category(CategoryType.guardian, 70, 110_000);

        var rakoto = new Employee(
            "Rakoto", "Be", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var pointing_rakoto = new Pointing(
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

        pointing_rakoto.calculAfterPointing();
        assertTrue(rakoto.getSalary().getNet() > gardien.getSalaryMatche() * 0.8);
    }

    @Test
    void pointing_with_night_work() {
        var calandar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));

        var gardien = new Category(CategoryType.guardian, 98, 110_000);

        var rabe = new Employee(
            "Rabe", "Be", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var pointing_rabe = new Pointing(
            rabe,
            calandar_juin,
            List.of(
                new Attendance(18, "17:00-07:00"),
                new Attendance(19, "17:00-07:00"),
                new Attendance(20, "17:00-07:00"),
                new Attendance(21, "17:00-07:00"),
                new Attendance(22, "17:00-07:00"),
                new Attendance(23, "17:00-07:00"),
                new Attendance(24, "17:00-07:00")
            )
        );

        pointing_rabe.calculAfterPointing();
        assertTrue(rabe.getSalary().getBrute() > gardien.getSalaryMatche() * 1.2);
    }

    double rounded(double number) {
        DecimalFormat df = new DecimalFormat("#");
        String numberRoundString = df.format(number);
        double numberRound = Double.parseDouble(numberRoundString);
        return numberRound;
    }
}
