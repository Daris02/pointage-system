package com.hei.app.pointage;

import java.time.Instant;

import com.hei.app.calendrier.Jour;
import com.hei.app.employe.Employe;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pointage {
    private Jour jours;
    private Instant begin;
    private Instant end;
    private Employe employe;
}
