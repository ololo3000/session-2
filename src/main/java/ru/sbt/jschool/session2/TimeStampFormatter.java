package ru.sbt.jschool.session2;

import java.text.SimpleDateFormat;

public class TimeStampFormatter implements Formatting {
    private SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm.SS.sss");

    @Override
    public String format(Object object) throws IllegalArgumentException {
        String formattedObj = "-";
        try {
            formattedObj = timeStampFormat.format(object);
        } catch (IllegalArgumentException e) {
            IllegalArgumentException se = new IllegalArgumentException("Format error");
            se.initCause(e);
            throw se;
        }
        return formattedObj;
    }
}
