package com.hei.app.employe;

import java.text.DecimalFormat;

import lombok.Data;

@Data
public class Salary {
    private double net;
    private double brute;

    public Salary(double brute) {
        this.brute = brute;
    }

    public double getBrute() {
        return roundDouble(brute);
    }

    public double getNet() {
        return roundDouble(brute * 0.8);
    }

    private double roundDouble(double number) {
        DecimalFormat df = new DecimalFormat("#.##");
        String numberRoundString = df.format(number);
        return Double.parseDouble(numberRoundString);
    }
}
