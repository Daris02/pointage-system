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
    private Employee employee;
    private final SpecialCalendar calendar;
    private List<Attendance> attendances;
    private int workHours = 0;
    private int hoursSupp = 0;
    private int holidaysSupp = 0;
    private int nightHoursSupp = 0;
    private int sundaySupp = 0;
    
    public Scoring(Employee employee, SpecialCalendar calendar, List<Attendance> attendances) {
        this.employee = employee;
        this.calendar = calendar;
        this.attendances = attendances;
    }

    public Employee calculAfterScoring() {
        for (Day day : calendar.getDays()) {
            for (Attendance attendance : attendances) {
                if (day.value() == attendance.getDay()) {
                    int workHourInDay = attendance.getWorkHours();
                    int defaultWorkHourPerDay = employee.getCategory().getWorkTime()/7;
                    workHours += workHourInDay;
                    if (workHourInDay > defaultWorkHourPerDay) hoursSupp += (workHourInDay - defaultWorkHourPerDay);
                    if (day.isHoliday()) holidaysSupp++;
                    if (day.name() == "Sunday" && employee.getCategory().getName() != CategoryType.guardian)
                        sundaySupp++;
                    if (attendance.containsNightHours()) {
                        nightHoursSupp += attendance.getNightWorkHours();
                    }
                }
            }
        }
        employee.addOverTime(hoursSupp);
        employee.addSundaySupp(sundaySupp);
        employee.addHolidaySupp(holidaysSupp);
        employee.addNightOverTime(nightHoursSupp);
        return employee;
    }
}
