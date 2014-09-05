package com.example.testactionbar.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;

@SuppressLint("SimpleDateFormat")
public class CalendarUtil
{
    public static final String FORMAT_MM_DD_HH_MM = "MM-dd hh:mm";

    public static void conversionCalendar(Calendar calendar, int numberOfYear, int numberOfMonth,
            int numberOfDay)
    {
        final int newYear = calendar.get(Calendar.YEAR) + numberOfYear;
        final int newMonth = calendar.get(Calendar.MONTH) + numberOfMonth;
        final int newDay = calendar.get(Calendar.DAY_OF_MONTH) + numberOfDay;
        calendar.set(newYear, newMonth, newDay);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDateString(Calendar calendar, String dateFormat)
    {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(calendar.getTime());
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDateString(Date date, String dateFormat)
    {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }

    public static String getCurrentDateFormat(String dateFormat)
    {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(new java.util.Date());
    }

}
