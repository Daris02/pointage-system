package com.hei.app.scoring;

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
        Duration duration = Duration.between(begin, end);
        int durationInHours = (int) duration.toHours();
        return durationInHours;
    }

    public int getNightWorkHours() {
        if (containsNightHours()) {
            Duration duration = Duration.between(end, LocalTime.of(6, 0));
            int durationInHours = (int) duration.toHours();
            return durationInHours;
        }
        return 0;
    }

    public boolean containsNightHours() {
        return begin.isAfter(LocalTime.of(18, 0)) || end.isBefore(LocalTime.of(6, 0));
    }
}
