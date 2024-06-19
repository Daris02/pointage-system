package com.hei.app.employe;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    private CategoryType name;
    private int workTime;
    private double salaryMatche;

    public double getIndemnity() {
        return 0.0;
    }
}
