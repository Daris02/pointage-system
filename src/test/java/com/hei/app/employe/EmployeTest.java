package com.hei.app.employe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.Test;

public class EmployeTest {
    
    @Test
    void salaire_net_exacte() {
        var gardien = new Category(CategoryType.guardian, 24, 100_000);

        var bob = new Employee(
            "Bob", "Doe", "5678",
            Instant.parse("2001-02-02T00:00:00Z"),
            Instant.parse("2020-07-12T00:00:00Z"),
            Instant.parse("2030-07-12T00:00:00Z"),
            gardien
        );

        assertEquals(80_000, bob.getSalary().getNet());
    }
}
