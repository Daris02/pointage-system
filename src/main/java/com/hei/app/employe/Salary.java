package com.hei.app.employe;

import lombok.Data;

@Data
public class Salary {
    private double brute;

    public Salary(double brute) {
        this.brute = brute;
    }

    public double getNet() {
        return brute * 0.8;
    }
}
