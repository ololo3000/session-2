package ru.sbt.jschool.session2;

public class StringFormatter implements Formatting {

    @Override
    public String format(Object object) {
        String formattedObj;
        if (object != null) {
            formattedObj = ((String) object).replace('\n', ' ');
        } else {
            formattedObj = "-";
        }
        return formattedObj;
    }
}
