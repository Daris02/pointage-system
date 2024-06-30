package com.hei.app.calendrier;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class SpecialCalendarTest {

    @Test
    void jour_ferier() {
        var calendar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));
        assertTrue(calendar_juin.getDays().get(17 -1).isHoliday());
    }

    @Test
    void jour_non_ferier() {
        var calendar_juin = new SpecialCalendar(6, 2024, List.of(17, 25, 26));
        assertFalse(calendar_juin.getDays().get(18 -1).isHoliday());
    }
}
