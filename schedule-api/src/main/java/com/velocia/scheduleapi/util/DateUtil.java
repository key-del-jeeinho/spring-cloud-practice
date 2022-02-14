package com.velocia.scheduleapi.util;

import java.time.LocalDate;
import java.time.YearMonth;


public class DateUtil {
    public static LocalDate getFirstDayOfMonth(Integer year, Integer month) {
        return LocalDate.of(year, month, 1);
    }
    public static LocalDate getLastDayOfMonth(Integer year, Integer month) {
        return YearMonth.of(year, month).atEndOfMonth();
    }
}
