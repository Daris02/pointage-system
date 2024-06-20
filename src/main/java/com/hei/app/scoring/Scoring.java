package com.hei.app.scoring;

import java.util.List;

import com.hei.app.calendrier.Day;
import com.hei.app.calendrier.SpecialCalendar;
import com.hei.app.employe.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Scoring {
    public final SpecialCalendar calendar;
    public List<Attendance> attendances;
    public Employee employee;

    public Employee calculAfterScoring() {
        for (Day day : calendar.getDays()) {
            for (Attendance attend : attendances) {
                if (day.value() == attend.day()) {
                    int workHours = convertInterval(attend.interval());
                    if (workHours > 8) employee.addHourSupp(workHours);
                    if (day.isHoliday()) employee.addHolidaySupp();
                    if (day.name() == "Sunday") employee.addSundaySupp();
                    if (intervalContainsNightHours(attend.interval())) employee.addNightHourSupp(workHours);
                }
            }
        }
        return employee;
    }

    private int convertInterval(String interval) {
        return 0;
    }

    private boolean intervalContainsNightHours(String interval) {
        return false;
    }
}
