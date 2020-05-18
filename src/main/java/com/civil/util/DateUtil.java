/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.util;

/**
 *
 * @author PCUser
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.joda.time.Days;

public class DateUtil {

    private static final String[] timeFormats
            = {
                "HH:mm:ss", "HH:mm"
            };
    private static final String[] dateSeparators
            = {
                "/", "-", " "
            };
    private static final String DMY_FORMAT = "dd{sep}MM{sep}yyyy";
    private static final String YMD_FORMAT = "yyyy{sep}MM{sep}dd";
    private static final String DMMYY_FORMAT = "dd{sep}MMM{sep}yyyy";
    private static final String ymd_template = "\\d{4}{sep}\\d{2}{sep}\\d{2}.*";
    private static final String dmy_template = "\\d{2}{sep}\\d{2}{sep}\\d{4}.*";
    private static final String dmmmyyy_template = "\\d{2}{sep}\\w{3}{sep}\\d{4}.*";

    public static Date stringToDate(String input) {
        Date date = null;
        String dateFormat = getDateFormat(input);
        if (dateFormat == null) {
            throw new IllegalArgumentException("Date is not in an accepted format " + input);
        }

        for (String sep : dateSeparators) {
            String actualDateFormat = patternForSeparator(dateFormat, sep);
            //try first with the time
            for (String time : timeFormats) {
                date = tryParse(input, actualDateFormat + " " + time);
                if (date != null) {
                    return date;
                }
            }
            //didn't work, try without the time formats
            date = tryParse(input, actualDateFormat);
            if (date != null) {
                return date;
            }
        }

        return date;
    }

    public static Calendar stringToCalendar(String input) {
        Date date = stringToDate(input);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private static String getDateFormat(String date) {
        for (String sep : dateSeparators) {
            String ymdPattern = patternForSeparator(ymd_template, sep);
            String dmyPattern = patternForSeparator(dmy_template, sep);
            String dmmmyPattern = patternForSeparator(dmmmyyy_template, sep);
            if (date.matches(ymdPattern)) {
                return YMD_FORMAT;
            }
            if (date.matches(dmyPattern)) {
                return DMY_FORMAT;
            }
            if (date.matches(dmmmyPattern)) {
                return DMMYY_FORMAT;
            }
        }
        return null;
    }

    private static String patternForSeparator(String template, String sep) {
        return template.replace("{sep}", sep);
    }

    private static Date tryParse(String input, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(input);
        } catch (ParseException e) {
        }
        return null;
    }

    public static Days_info getDateDuration(Date d1, Date d2) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        try {

            DateTime dt1 = new DateTime(d1);
            DateTime dt2 = new DateTime(d2);
            Days_info di = new Days_info();

            di.setDays(Days.daysBetween(dt1, dt2).getDays());
            di.setHours(Hours.hoursBetween(dt1, dt2).getHours() % 24);
            di.setMiniutes(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60);
            di.setSeconds(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60);
            return di;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
