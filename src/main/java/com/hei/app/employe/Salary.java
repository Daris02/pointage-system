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
        setBrute(roundDouble(brute));
        setNet(roundDouble(brute * 0.8));
        return net;
    }

    private double roundDouble(double number) {
        DecimalFormat df = new DecimalFormat("#.##");
        String numberRoundString = df.format(number);
        double numberRound = Double.parseDouble(numberRoundString);
        return numberRound;
    }
}
