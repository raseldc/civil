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

import java.util.Date;

/**
 *
 * @author sarwar
 */
public class DatePropertyEditor extends PropertyEditorSupport {

    public DatePropertyEditor() {
    }

    // Converts a String to a Calendar (when submitting form)
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text.contains(":")) {
            setValue(parseDate(text));
        }
        if (text == null || text == "") {
            setValue(null);
        } else {
            setValue(tryParse(text));
        }
    }

    // Converts a Calendar to a String (when displaying form)
    @Override
    public String getAsText() throws IllegalArgumentException {
        Date text = (Date) this.getValue();
//        //System.out.println("text=" + text);
        String format;
        if (text != null) {
            System.out.println("Find 1 " + ApplicationSettings.DEFAULT_DATE_FORMAT);
            format = ApplicationSettings.DEFAULT_DATE_FORMAT;
        } else {
            format = ApplicationSettings.DEFAULT_DATE_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (text == null) {
            return "";
        }
        String timeInString = sdf.format(text.getTime());
//        //System.out.println("timeInString = " + timeInString);
        return timeInString;
    }

    private Date tryParse(String dateString) {
//        //System.out.println("dateString=" + dateString);
        String formatString = ApplicationSettings.DEFAULT_DATE_FORMAT;
        try {
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

            //   SimpleDateFormat sdf = new SimpleDateFormat(formatString);
            Date date = dt.parse(dateString);

            return date;
        } catch (ParseException e) {
            //System.out.println("tryParse(): in parse exception");
        }
        return null;
    }

    private Date parseDate(String text) {
//        //System.out.println("text=" + text);

        Date d = DateUtil.stringToDate(text);

        return d;
    }
}
