package ru.sbt.jschool.session2;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class NumberFormatter implements Formatting {
    private DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    private DecimalFormat numberFormat;

    public NumberFormatter() {
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator(',');
        numberFormat = new DecimalFormat("###,###", symbols);
    }

    @Override
    public String format(Object object) {
        String formattedObj = "-";
        try {
            formattedObj = numberFormat.format(object);
        } catch (IllegalArgumentException e) {
            //formattedObj = "-";
        }
        return formattedObj;
    }
}
