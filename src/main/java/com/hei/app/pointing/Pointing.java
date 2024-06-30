package com.hei.app.pointing;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private List<SpecialCalendar> calendars;
    private List<Set<Attendance>> allAttendances;
    private int workHours = 0;
    private int overTime = 0;
    private int additionalHolidays = 0;
    private int nightOverTime = 0;
    private int additionalSunday = 0;
    private double sumOfHS = 0.0;
    private double sumOfHM = 0.0;
    
    public Pointing(Employee employee, List<SpecialCalendar> calendars, List<Set<Attendance>> allAttendances) {
        this.employee = employee;
        this.calendars = calendars;
        this.allAttendances = allAttendances;
    }

    public void calculAfterPointing() {
        for (int i = 0; i < calendars.size(); i++) calculAfterPointingMonth(calendars.get(i), allAttendances.get(i));
        employee.getSalary().setBrute(sumOfHS + sumOfHM);
    }

    public void calculAfterPointingMonth(SpecialCalendar calendar, Set<Attendance> attendances) {
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
        for (List<Day> weekOfMonth : month) calculAfterPointingInWeek(weekOfMonth, attendances);
    }

    private void calculAfterPointingInWeek(List<Day> calendar, Set<Attendance> attendances) {
        List<Day> checkDays = new ArrayList<>();
        for (Day day : calendar) {
            for (Attendance attendance : attendances) {
                if (day.value() == attendance.getDay()) {
                    int workHourInDay = attendance.getWorkHours();
                    int defaultWorkHourPerDay = employee.getCategory().getWorkTime()/ 7;
                    int overTimeInDay = 0;

                    if (checkDays.contains(day)) {
                        if (attendance.containsNightHours()) overTimeInDay += attendance.getNightWorkHours();
                        else overTimeInDay += workHourInDay;
                        overTime += overTimeInDay;
                    } else if (!checkDays.contains(day) && workHourInDay > defaultWorkHourPerDay && employee.getCategory().getName() != CategoryType.senior) {
                        if (attendance.containsNightHours()) overTimeInDay += attendance.getNightWorkHours();
                        else overTimeInDay += (workHourInDay - defaultWorkHourPerDay);
                        overTime += overTimeInDay;
                    }
                    checkDays.add(day);

                    List<Double> listOfHM = new ArrayList<>();
                    if (attendance.containsNightHours()) {
                        nightOverTime += attendance.getNightWorkHours();
                        listOfHM.add(1.3);
                    } else { listOfHM.add(1.0); }
                    if (day.name().equals("Sunday")) {
                        additionalSunday += workHourInDay;
                        listOfHM.add(1.4);
                    } else { listOfHM.add(1.0); }
                    if (day.isHoliday()) {
                        additionalHolidays += workHourInDay;
                        listOfHM.add(1.5);
                    } else { listOfHM.add(1.0); }
                    workHourInDay -= overTimeInDay;
                    workHours += workHourInDay;
                    sumOfHS += employee.addOverTime(overTimeInDay, (listOfHM.get(0) * listOfHM.get(1) * listOfHM.get(2)));
                    sumOfHM += employee.addIncreasedTime(workHourInDay, (listOfHM.get(0) * listOfHM.get(1) * listOfHM.get(2)));
                }
            }
        }
    }
}
