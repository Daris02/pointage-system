package com.hei.app.employe;

import lombok.Data;

@Data
public class Salary {
    private double net;
    private double brute;

    public Salary(double brute) {
        this.brute = brute;
        this.net = brute * 0.8;
    }
}
