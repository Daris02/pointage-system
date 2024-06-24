package com.hei.app.scoring;

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
public class Scoring {
    private Employee employee;
    private SpecialCalendar calendar;
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

    public void calculAfterScoring() {
        List<List<Day>> month = new ArrayList<>();
        List<Day> week = new ArrayList<>();
        for (Day day : calendar.getDays()) {
            if (week.size() <= 7) week.add(day);
            else {
                month.add(week);
                week = new ArrayList<>();
                week.add(day);
            }
        }
        for (List<Day> weekOfMonth : month) {
            employee = calculAfterScoringInWeek(weekOfMonth);
        }
    }

    private Employee calculAfterScoringInWeek(List<Day> calendar) {
        int workHoursInWeek = 0;
        int hoursSuppInWeek = 0;
        int holidaysSuppInWeek = 0;
        int nightHoursSuppInWeek = 0;
        int sundaySuppInWeek = 0;
        for (Day day : calendar) {
            for (Attendance attendance : attendances) {
                if (day.value() == attendance.getDay()) {
                    int workHourInDay = attendance.getWorkHours();
                    int defaultWorkHourPerDay = employee.getCategory().getWorkTime()/7;
                    workHoursInWeek += workHourInDay;
                    if (workHourInDay > defaultWorkHourPerDay) hoursSuppInWeek += (workHourInDay - defaultWorkHourPerDay);
                    if (attendance.containsNightHours()) {
                        hoursSuppInWeek += attendance.getNightWorkHours();
                        nightHoursSuppInWeek += attendance.getNightWorkHours();
                    }
                    if (day.isHoliday()) holidaysSuppInWeek += workHourInDay;
                    if (day.name() == "Sunday" && employee.getCategory().getName() != CategoryType.guardian)
                        sundaySuppInWeek += workHourInDay;
                }
            }
        }
        setWorkHours(workHours += workHoursInWeek);
        setHoursSupp(hoursSupp += hoursSuppInWeek);;
        setHolidaysSupp(holidaysSupp += holidaysSuppInWeek);;
        setNightHoursSupp(nightHoursSupp += nightHoursSuppInWeek);;
        setSundaySupp(sundaySupp += sundaySuppInWeek);
        if (hoursSupp > 20) hoursSupp = 20;
        employee.addOverTime(hoursSupp);
        employee.addSundaySupp(sundaySupp);
        employee.addHolidaySupp(holidaysSupp);
        employee.addNightOverTime(nightHoursSupp);
        return employee;
    }
}
