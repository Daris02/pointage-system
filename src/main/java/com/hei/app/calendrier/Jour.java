package com.hei.app.calendrier;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Jour {
    private int value;
    private boolean isFerier;
}
