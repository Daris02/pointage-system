package com.hei.app.employe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.hei.app.calendrier.SpecialCalendar;
import com.hei.app.pointing.Attendance;
import com.hei.app.pointing.Pointing;

public class EmployeTest {
    
    @Test
    void valid_working_hour() {
        var gardien = new Category(CategoryType.guardian, 70, 100_000);

        var rakoto = new Employee(
            "Rakoto", "N.", "1234",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        var calandarMay = new SpecialCalendar(5, 2024, List.of());
        var calandarJune = new SpecialCalendar(6, 2024, List.of(26));
        var calandarJuly = new SpecialCalendar(7, 2024, List.of());
        
        Set<Attendance> listAttendanceMay = new LinkedHashSet<>();
            for (int i = 26; i <= 31; i++) listAttendanceMay.add(new Attendance(i, "07:00-17:00"));
        Set<Attendance> listAttendanceJune = new LinkedHashSet<>();
            for (int i = 1; i <= 30; i++) listAttendanceJune.add(new Attendance(i, "07:00-17:00"));
        Set<Attendance> listAttendanceJuly = new LinkedHashSet<>();
            for (int i = 1; i <= 6; i++) listAttendanceJuly.add(new Attendance(i, "07:00-17:00"));

        var pointingRakoto = new Pointing(rakoto,
                List.of(calandarMay, calandarJune, calandarJuly),
                List.of(listAttendanceMay, listAttendanceJune, listAttendanceJuly));
        pointingRakoto.calculAfterPointing();

        assertEquals(420, pointingRakoto.getWorkHours());

        assertEquals(0, pointingRakoto.getOverTime());

        // True = 641_428.57
        assertTrue( 640_00 < rakoto.getSalary().getBrute() && rakoto.getSalary().getBrute() < 642_000);
        assertTrue(640_000 * 0.8 < rakoto.getSalary().getNet() && rakoto.getSalary().getNet() < 642_000 * 0.8);
    }

    @Test
    void valid_night_working_hour() {
        var gardien = new Category(CategoryType.guardian, 98, 100_000);

        var rabe = new Employee(
                "Rabe", "N.", "1234",
                "2001-02-02",
                "2020-07-12",
                "2030-07-12",
                gardien
        );

        var calandarMay = new SpecialCalendar(5, 2024, List.of());
        var calandarJune = new SpecialCalendar(6, 2024, List.of(26));
        var calandarJuly = new SpecialCalendar(7, 2024, List.of());

        Set<Attendance> listAttendanceMay = new LinkedHashSet<>();
            for (int i = 26; i <= 31; i++) listAttendanceMay.add(new Attendance(i, "17:00-07:00"));
        Set<Attendance> listAttendanceJune = new LinkedHashSet<>();
            for (int i = 1; i <= 30; i++) listAttendanceJune.add(new Attendance(i, "17:00-07:00"));
        Set<Attendance> listAttendanceJuly = new LinkedHashSet<>();
            for (int i = 1; i <= 6; i++) listAttendanceJuly.add(new Attendance(i, "17:00-07:00"));

        var pointingRabe = new Pointing(rabe,
                List.of(calandarMay, calandarJune, calandarJuly),
                List.of(listAttendanceMay, listAttendanceJune, listAttendanceJuly));
        pointingRabe.calculAfterPointing();

        assertEquals(588, pointingRabe.getWorkHours());
        assertEquals(0, pointingRabe.getOverTime());

        // True = 833_000.0
        assertTrue(832_000 < rabe.getSalary().getBrute() && rabe.getSalary().getBrute() < 834_000);
        assertTrue(832_000 * 0.8 < rabe.getSalary().getNet() && rabe.getSalary().getNet() < 834_000 * 0.8);
    }

    @Test
    void valid_working_with_night_overtime() {
        var gardien = new Category(CategoryType.guardian, 70, 100_000);

        var rakoto = new Employee(
                "Rakoto", "N.", "1234",
                "2001-02-02",
                "2020-07-12",
                "2030-07-12",
                gardien
        );

        var calandarMay = new SpecialCalendar(5, 2024, List.of());
        var calandarJune = new SpecialCalendar(6, 2024, List.of(26));
        var calandarJuly = new SpecialCalendar(7, 2024, List.of());

        Set<Attendance> listAttendanceMay = new LinkedHashSet<>();
            for (int i = 26; i <= 31; i++) {
                listAttendanceMay.add(new Attendance(i, "07:00-17:00"));
                if (List.of(26, 27, 28, 29, 30, 31).contains(i))
                    listAttendanceMay.add(new Attendance(i, "17:00-07:00"));
            }

        Set<Attendance> listAttendanceJune = new LinkedHashSet<>();
            for (int i = 1; i <= 30; i++) listAttendanceJune.add(new Attendance(i, "07:00-17:00"));
        Set<Attendance> listAttendanceJuly = new LinkedHashSet<>();
            for (int i = 1; i <= 6; i++) listAttendanceJuly.add(new Attendance(i, "07:00-17:00"));

        var pointingRakoto = new Pointing(rakoto,
                List.of(calandarMay, calandarJune, calandarJuly),
                List.of(listAttendanceMay, listAttendanceJune, listAttendanceJuly));
        pointingRakoto.calculAfterPointing();

        // True = 872_007.414
        assertTrue( 872_000 < rakoto.getSalary().getBrute() && rakoto.getSalary().getBrute() < 873_000);
        assertTrue(872_000 * 0.8 < rakoto.getSalary().getNet() && rakoto.getSalary().getNet() < 873_000 * 0.8);
    }

    @Test
    void correct_net_salary() {
        var gardien = new Category(CategoryType.normal, 40, 100_000);

        var john = new Employee(
            "John", "Doe", "5678",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        john.setSalary(new Salary(john.getCategory().getSalaryMatche()));
        assertEquals(80_000, john.getSalary().getNet());
    }
}
