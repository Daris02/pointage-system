package com.hei.app.calendrier;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lombok.Data;

@Data
public class SpecialCalendar {
    private final int month;
    private List<Day> days = new ArrayList<>();

    public SpecialCalendar(int month, int years, List<Integer> holidays) {
        this.month = month;
        for (int i = 1; i <= numberDaysOfMonth(month, years); i++) {
            String dayName = getDayName(years, month, i);
            if (holidays.contains(i)) days.add(new Day(i, dayName,true));
            else days.add(new Day(i, dayName,false));
        }
    }

    private static int numberDaysOfMonth(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (isLeapYear(year)) return 29;
                return 28;
            default:
                throw new IllegalArgumentException("Month not valid : " + month);
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
    }

    private static String getDayName(int year, int month, int day) {
        return LocalDate.of(year, month, day).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
}
