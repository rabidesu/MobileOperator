package com.tsystems.jschool.mobile.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static Date parseDate(String date) throws ParseException {
        if (date == null) throw new ParseException("Date not found", 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d");
        sdf.setLenient(false);
        return sdf.parse(date);
    }
}
