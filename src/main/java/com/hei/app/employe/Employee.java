package com.hei.app.employe;

import java.time.Instant;

import lombok.Data;

@Data
public class Employee {
    private String firstName;
    private String lastName;
    private String registrationNumber;
    private Instant birthDate;
    private Instant hireDate;
    private Instant endContrat;
    private Salary salary;
    private Category category;

    public Employee(String firstName, String lastName, String registrationNumber, 
            String birthDate, String hireDate, String endContrat, Category category) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationNumber = registrationNumber;
        this.birthDate = Instant.parse(birthDate+"T00:00:00Z");
        this.hireDate = Instant.parse(hireDate+"T00:00:00Z");
        this.endContrat = Instant.parse(endContrat+"T00:00:00Z");
        this.category = category;
        this.salary  = new Salary(0);
    }

    public void updateSalary(int hours) {
        setSalary(new Salary(salary.getBrute() + salaryPerHour() * hours));
    }

    public void addOverTime(int hours) {
        double salaryHoursSupp = 0;
        if (hours <= 8) salaryHoursSupp += salaryPerHour() * 0.3 * hours;
        
        int lastOverTime = hours - 8;
        if (lastOverTime >= 0) salaryHoursSupp += salaryPerHour() * 0.5 * lastOverTime;
        salary.setBrute(salary.getBrute() + salaryHoursSupp);
    }
    
    public void addNightOverTime(int hours) {
        double salaryHoursSupp = salaryPerHour() * 0.3 * hours;
        salary.setBrute(salary.getBrute() + salaryHoursSupp);
    }

    public void addSundaySupp(int hours) {
        double salaryHoursSupp = salaryPerHour() * 0.4 * hours;
        salary.setBrute(salary.getBrute() + salaryHoursSupp);
    }

    public void addHolidaySupp(int hours) {
        double salaryHoursSupp = salaryPerHour() * 0.5 * hours;
        salary.setBrute(salary.getBrute() + salaryHoursSupp);
    }

    private double salaryPerHour() {
        return category.getSalaryMatche()/ category.getWorkTime();
    }
}
