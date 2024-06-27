package com.hei.app.pointing;

import java.util.ArrayList;
import java.util.List;

import com.hei.app.calendrier.Day;
import com.hei.app.calendrier.SpecialCalendar;
import com.hei.app.employe.CategoryType;
import com.hei.app.employe.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pointing {
    private Employee employee;
    private SpecialCalendar calendar;
    private List<Attendance> attendances;
    private int workHours = 0;
    private int overTime = 0;
    private int additionalHolidays = 0;
    private int nightOverTime = 0;
    private int additionalSunday = 0;
    
    public Pointing(Employee employee, SpecialCalendar calendar, List<Attendance> attendances) {
        this.employee = employee;
        this.calendar = calendar;
        this.attendances = attendances;
    }

    public void calculAfterPointing() {
        List<List<Day>> month = new ArrayList<>();
        List<Day> week = new ArrayList<>();
        for (Day day : calendar.getDays()) {
            if (week.size() < 7) {
                week.add(day);
                if (calendar.getDays().getLast() == day) month.add(week);
            } else {
                month.add(week);
                week = new ArrayList<>();
                week.add(day);
            }
        }
        for (List<Day> weekOfMonth : month) {
                calculAfterPointingInWeek(weekOfMonth);
        }
    }

    private void calculAfterPointingInWeek(List<Day> calendar) {
        int workHoursInWeek = 0;
        int overTimeInWeek = 0;
        int additionalHolidaysInWeek = 0;
        int nightOverTimeInWeek = 0;
        int additionalSundayInWeek = 0;
        for (Day day : calendar) {
            for (Attendance attendance : attendances) {
                if (day.value() == attendance.getDay()) {
                    int workHourInDay = attendance.getWorkHours();
                    int defaultWorkHourPerDay = employee.getCategory().getWorkTime()/7;
                    workHoursInWeek += workHourInDay;

                    if (workHourInDay > defaultWorkHourPerDay) overTimeInWeek += (workHourInDay - defaultWorkHourPerDay);
                    if (attendance.containsNightHours()) nightOverTimeInWeek += attendance.getNightWorkHours();
                    if (day.isHoliday()) additionalHolidaysInWeek += workHourInDay;
                    if (day.name().equals("Sunday") && employee.getCategory().getName() != CategoryType.guardian)
                        additionalSundayInWeek += workHourInDay;
                }
            }
        }
        workHours += workHoursInWeek;
        overTime += overTimeInWeek;
        additionalHolidays += additionalHolidaysInWeek;
        nightOverTime += nightOverTimeInWeek;
        additionalSunday += additionalSundayInWeek;
        if (overTimeInWeek > 20) overTimeInWeek = 20;
        employee.updateSalary(workHoursInWeek);
        employee.addOverTime(overTimeInWeek);
        employee.addNightOverTime(nightOverTimeInWeek);
        employee.additionalSunday(additionalSundayInWeek);
        employee.additionalHolidays(additionalHolidaysInWeek);
    }
}
