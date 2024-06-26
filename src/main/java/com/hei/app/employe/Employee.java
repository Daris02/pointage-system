package com.hei.app.employe;

import java.text.DecimalFormat;
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
        if (hours <= 8) salaryHoursSupp += salaryPerHour() * 1.3 * hours;
        
        int lastOverTime = hours - 8;
        if (lastOverTime >= 0) salaryHoursSupp += salaryPerHour() * 1.5 * lastOverTime;
        salary.setBrute(salary.getBrute() + salaryHoursSupp);
    }
    
    public void addNightOverTime(int hours) {
        double salaryHoursSupp = salaryPerHour() * 1.3 * hours;
        salary.setBrute(salary.getBrute() + salaryHoursSupp);
    }

    public void addSundaySupp(int hours) {
        salary.setBrute(salary.getBrute() + (salaryPerHour() * 1.4 * hours));
    }

    public void addHolidaySupp(int hours) {
        salary.setBrute(salary.getBrute() + (salaryPerHour() * 1.5 * hours));
    }

    public double salaryPerHour() {
        return roundDouble(category.getSalaryMatche()/ category.getWorkTime());
    }

    private double roundDouble(double number) {
        DecimalFormat df = new DecimalFormat("#.###");
        String numberRoundString = df.format(number);
        double numberRound = Double.parseDouble(numberRoundString);
        return numberRound;
    }
}
