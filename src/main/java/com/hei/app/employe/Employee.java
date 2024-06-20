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

    public Employee(String firstName, String lastName, String registrationNumber, Instant birthDate, Instant hireDate,
            Instant endContrat, Category category) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationNumber = registrationNumber;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.endContrat = endContrat;
        this.category = category;
        this.salary  = defaultSalary();
    }

    public Salary defaultSalary() {
        return new Salary(category.getSalaryMatche());
    }

    public double addHourSupp(int hours) {
        if (hours >= 8) salary = new Salary(salary.getBrute() * 1.3);
        if (hours >= 12) salary = new Salary(salary.getBrute() * 1.5);
        return 0.0;
    }
    
    public void addNightHourSupp(int hours) {
        this.salary = new Salary(salary.getBrute() * 1.3);
    }

    public void addSundaySupp() {
        this.salary = new Salary(salary.getBrute() * 1.4);
    }

    public void addHolidaySupp() {
        this.salary = new Salary(salary.getBrute() * 1.5);
    }
}
