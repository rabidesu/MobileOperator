package com.tsystems.jschool.mobile.services;

import com.tsystems.jschool.mobile.services.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtilsTest {

    @Test
    public void parseCorrectDate() throws ParseException {
        Calendar c1 = new GregorianCalendar(1993, Calendar.FEBRUARY, 19);
        Date curDate = c1.getTime();
        Date date = Utils.parseDate("1993-02-19");
        Assert.assertTrue(date.getTime() == curDate.getTime());
    }

    @Test(expected = ParseException.class)
    public void parseIncorrectDate() throws ParseException {
        Utils.parseDate("1993-25-19");
    }

    @Test(expected = ParseException.class)
    public void parseNoDate() throws ParseException {
        Utils.parseDate("noDate");
    }

    @Test(expected = ParseException.class)
    public void parseNull() throws ParseException {
        Utils.parseDate(null);
    }


}
