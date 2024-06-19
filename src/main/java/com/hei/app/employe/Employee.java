package com.hei.app.employe;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private String firstName;
    private String lastName;
    private String registrationNumber;
    private Instant birthDate;
    private Instant hireDate;
    private Instant endContrat;
    private Category category;

    public double getNetSalary() {
        double salaireBrute = category.getSalaryMatche();
        return salaireBrute * 0.8;
    }
}
