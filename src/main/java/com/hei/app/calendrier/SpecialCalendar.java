package com.hei.app.calendrier;

import java.util.List;

import lombok.Data;

@Data
public class SpecialCalendar {
    private final int mois;
    private static List<Jour> jours;

    public SpecialCalendar(int mois, int annees, List<Integer> jourFerier) {
        this.mois = mois;
        for (int i = 1; i <= nombreJoursMois(mois, annees); i++) {
            if (jourFerier.contains(i)) jours.add(new Jour(i, true));
            else jours.add(new Jour(i, false));
        }
    }

    private static int nombreJoursMois(int mois, int annee) {
        switch (mois) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (estBissextile(annee)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                throw new IllegalArgumentException("Mois invalide : " + mois);
        }
    }

    private static boolean estBissextile(int annee) {
        return (annee % 4 == 0) && ((annee % 100 != 0) || (annee % 400 == 0));
    }
}
