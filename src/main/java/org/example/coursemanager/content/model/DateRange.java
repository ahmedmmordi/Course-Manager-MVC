package org.example.coursemanager.content.model;

import lombok.Getter;

@Getter
public enum DateRange {
    LAST_MONTH(1),
    LAST_3_MONTHS(3),
    LAST_6_MONTHS(6),
    LAST_YEAR(12),
    ALL_TIME(0);

    private final int months;
    DateRange(int months) {
        this.months = months;
    }
}
