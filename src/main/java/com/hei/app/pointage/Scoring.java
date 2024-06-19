package com.hei.app.pointage;

import java.time.Instant;

import com.hei.app.calendrier.Day;
import com.hei.app.employe.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Scoring {
    private Day days;
    private Instant begin;
    private Instant end;
    private Employee employee;
}
