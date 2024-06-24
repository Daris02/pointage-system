package com.hei.app.employe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EmployeTest {
    
    @Test
    void correct_net_salary() {
        var gardien = new Category(CategoryType.normal, 40, 100_000);

        var john = new Employee(
            "John", "Doe", "5678",
            "2001-02-02",
            "2020-07-12",
            "2030-07-12",
            gardien
        );

        assertEquals(80_000, john.getSalary().getNet());
    }
}
