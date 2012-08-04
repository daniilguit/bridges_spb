package com.ad.bridges_spb.core;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: daniil
 * Date: 15.07.12
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
public class Time {
    public final int hour;
    public final int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public String toString() {
        return String.format("%d:%02d", hour, minute);
    }

    public Calendar nearestDateTime(Calendar now) {
        Calendar calendar = today(now);
        if (calendar.getTimeInMillis() < now.getTimeInMillis()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return calendar;
    }

    public Calendar today(Calendar now) {
        Calendar calendar = (Calendar) now.clone();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
}
