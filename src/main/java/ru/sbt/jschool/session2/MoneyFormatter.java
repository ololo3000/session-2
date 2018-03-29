package ru.sbt.jschool.session2;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class MoneyFormatter implements Formatting {
    private DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    private DecimalFormat moneyFormat;

    public MoneyFormatter() {
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator(',');
        moneyFormat = new DecimalFormat("###,##0.00", symbols);
    }

    @Override
    public String format(Object object) throws IllegalArgumentException {
        String formattedObj = "-";
        try {
            formattedObj = moneyFormat.format(object);
        } catch (IllegalArgumentException e) {
            IllegalArgumentException se = new IllegalArgumentException("Format error");
            se.initCause(e);
            throw se;
        }
        return formattedObj;
    }
}
