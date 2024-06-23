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
            Instant birthDate, Instant hireDate, Instant endContrat, Category category) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationNumber = registrationNumber;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.endContrat = endContrat;
        this.category = category;
        this.salary  = new Salary(category.getSalaryMatche());
    }

    public void addHourSupp(int hours) {
        double newSalary = 0;
        if (hours <= 8 && hours < 12) newSalary = salary.getBrute() * 1.3;
        if (hours >= 12) newSalary = salary.getBrute() * 1.5;
        salary.setBrute(salary.getBrute() + newSalary);
    }
    
    public void addNightHourSupp(int hours) {
        salary.setBrute(salary.getBrute() * 1.3);
    }

    public void addSundaySupp(int numberOfDay) {
        if (numberOfDay > 0) salary.setBrute(salary.getBrute() * 1.4 * numberOfDay);
    }

    public void addHolidaySupp(int numberOfDay) {
        if (numberOfDay > 0) salary.setBrute(salary.getBrute() * 1.5 * numberOfDay);
    }
}
