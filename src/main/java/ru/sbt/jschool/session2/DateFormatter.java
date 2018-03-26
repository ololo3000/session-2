package ru.sbt.jschool.session2;

import java.text.SimpleDateFormat;

public class DateFormatter implements Formatting {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public String format(Object object) {
        String formattedObj = "-";
        try {
            formattedObj = dateFormat.format(object);
        } catch (IllegalArgumentException e) {
            //formattedObj = "-";
        }
        return formattedObj;
    }
}
