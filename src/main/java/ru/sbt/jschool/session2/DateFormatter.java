package ru.sbt.jschool.session2;

import java.text.SimpleDateFormat;

public class DateFormatter implements Formatting {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public String format(Object object) throws IllegalArgumentException {
        String formattedObj = "-";
        try {
            formattedObj = dateFormat.format(object);
        } catch (IllegalArgumentException e) {
            IllegalArgumentException se = new IllegalArgumentException("Format error");
            se.initCause(e);
            throw se;
        }
        return formattedObj;
    }
}
