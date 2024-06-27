package com.hei.app.pointing;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class Attendance {
    private int day;
    private LocalTime begin;
    private LocalTime end;

    public Attendance(int day, String interval) {
        this.day = day;
        String[] parts = interval.split("-");
        if (parts.length != 2) throw new RuntimeException("Intervalle format not valid : " + interval);
        this.begin = LocalTime.parse(parts[0], DateTimeFormatter.ofPattern("HH:mm"));
        this.end = LocalTime.parse(parts[1], DateTimeFormatter.ofPattern("HH:mm"));
    }

    public int getWorkHours() {
        if (containsNightHours())
            return Duration.between(begin.plusHours(10), end.plusHours(10)).toHoursPart();
        return Duration.between(begin, end).toHoursPart();
    }

    public int getNightWorkHours() {
        if (containsNightHours())
            return Duration.between(begin.plusHours(10), end.plusHours(10)).toHoursPart();
        return 0;
    }

    public boolean containsNightHours() {
        return begin.isAfter(LocalTime.of(17, 0)) || begin.getHour() == 17 ||
            end.isBefore(LocalTime.of(7, 0)) || end.getHour() == 7;
    }
}
