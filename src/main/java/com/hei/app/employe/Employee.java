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
}
