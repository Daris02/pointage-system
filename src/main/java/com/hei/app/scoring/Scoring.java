package com.hei.app.scoring;

import java.util.List;

import com.hei.app.calendrier.Day;
import com.hei.app.calendrier.SpecialCalendar;
import com.hei.app.employe.CategoryType;
import com.hei.app.employe.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Scoring {
    public Employee employee;
    public final SpecialCalendar calendar;
    public List<Attendance> attendances;

    public Employee calculAfterScoring() {
        int workHoursSupp = 0;
        int holidaysSupp = 0;
        int nightHoursSupp = 0;
        int sundaySupp = 0;
        for (Day day : calendar.getDays()) {
            for (Attendance attendance : attendances) {
                if (day.value() == attendance.getDay()) {
                    int workHours = attendance.getWorkHours();
                    if (workHours > 0) workHoursSupp += workHours;
                    if (day.isHoliday()) holidaysSupp++;
                    if (day.name() == "Sunday" && employee.getCategory().getName() != CategoryType.guardian)
                        sundaySupp++;
                    if (attendance.containsNightHours()) {
                        nightHoursSupp += attendance.getNightWorkHours();
                    }
                }
            }
        }
        employee.addHourSupp(workHoursSupp);
        employee.addSundaySupp(sundaySupp);
        employee.addHolidaySupp(holidaysSupp);
        employee.addNightHourSupp(nightHoursSupp);
        return employee;
    }
}
