/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.binder;


import com.civil.util.ApplicationSettings;
import com.civil.util.DateUtil;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author sarwar
 */
public class CalendarPropertyEditor extends PropertyEditorSupport {

    public CalendarPropertyEditor() {
    }

    // Converts a String to a Calendar (when submitting form)
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text.contains(":")) {
            setValue(parseDate(text));
        } else {
            setValue(tryParse(text));
        }
    }

    // Converts a Calendar to a String (when displaying form)
    @Override
    public String getAsText() throws IllegalArgumentException {
        Calendar text = (Calendar) this.getValue();
//        //System.out.println("text=" + text);
        String format;
        if (text != null && text.get(Calendar.HOUR_OF_DAY) == 0 && text.get(Calendar.MINUTE) == 0 && text.get(Calendar.SECOND) == 0) {
            System.out.println("Find 1 " + ApplicationSettings.DEFAULT_DATE_FORMAT);
            format = ApplicationSettings.DEFAULT_DATE_FORMAT;
        } else {
            format = ApplicationSettings.DEFAULT_DATE_TIME_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (text == null) {
            return "";
        }
        String timeInString = sdf.format(text.getTime());
//        //System.out.println("timeInString = " + timeInString);
        return timeInString;
    }

    private Calendar tryParse(String dateString) {
//        //System.out.println("dateString=" + dateString);
        String formatString = ApplicationSettings.DEFAULT_DATE_FORMAT;
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(formatString);
            Date date = sdf.parse(dateString);
            cal.setTime(date);
            return cal;
        } catch (ParseException e) {
            //System.out.println("tryParse(): in parse exception");
        }
        return null;
    }

    private Calendar parseDate(String text) {
//        //System.out.println("text=" + text);
        Calendar cal = Calendar.getInstance();
        Date d = DateUtil.stringToDate(text);
        cal.setTime(d);
        return cal;
    }
}
