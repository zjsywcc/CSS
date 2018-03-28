package com.windbise.css.util;

import java.time.LocalDateTime;

/**
 * Created by wangchengcheng on 2018/3/28.
 */
public class DateTimeUtil {

    public static int getDecades() {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        return year % 100;
    }

    public static int getMonth() {
        LocalDateTime now = LocalDateTime.now();
        int month = now.getMonthValue();
        return month;
    }

    public static int getDay() {
        LocalDateTime now = LocalDateTime.now();
        int day = now.getDayOfMonth();
        return day;
    }

    public static int getHour() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        return hour;
    }

    public static int getMinute() {
        LocalDateTime now = LocalDateTime.now();
        int minute = now.getMinute();
        return minute;
    }

    public static int getSeconds() {
        LocalDateTime now = LocalDateTime.now();
        int second = now.getSecond();
        return second;
    }
}
