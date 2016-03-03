package com.tsystems.jschool.mobile.services;

import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexandra on 03.03.2016.
 */
public class Utils {

    public static Date parseDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d");
        return sdf.parse(date);
    }
}
