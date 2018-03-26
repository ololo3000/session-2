package ru.sbt.jschool.session2;

import java.text.SimpleDateFormat;

public class TimeSampFormatter implements Formatting {
    private SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm.SS.sss");

    @Override
    public String format(Object object) {
        String formattedObj = "-";
        try {
            formattedObj = timeStampFormat.format(object);
        } catch (IllegalArgumentException e) {
            //formattedObj = "-";
        }
        return formattedObj;
    }
}
