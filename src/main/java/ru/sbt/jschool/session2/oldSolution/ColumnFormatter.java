package ru.sbt.jschool.session2.oldSolution;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ColumnFormatter {
    public enum ColumnType {
        NUMBER,
        MONEY,
        DATE,
        STRING
    }

    private String title;
    private ColumnType type;
    private ArrayList<String> data = new ArrayList<>();;
    private int maxDataLenght = 0;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    private DecimalFormat moneyFormat;
    private DecimalFormat numberFormat;

    public ColumnFormatter(ColumnType type) {
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator(',');
        moneyFormat = new DecimalFormat("###,##0.00", symbols);
        numberFormat = new DecimalFormat("###,###", symbols);
        this.type = type;
    }

    public int getMaxDataLenght() {
        return this.maxDataLenght;
    }

    public String getTitle() {
        return this.title;
    }


    public ColumnType getType() {
        return this.type;
    }

    public void addTitle(String title) {
        this.title = title;
        if (title.length() > maxDataLenght) {
            maxDataLenght = title.length();
        }
    }

    public String getElement(int index) {
        return data.get(index);
    }

    public void addElement(Object element) {
        switch (type) {
            case DATE: {
                data.add(dateFormat.format(element));
                break;
            }
            case NUMBER: {
                try {
                    data.add(numberFormat.format(element));
                } catch (Exception e) {
                    data.add("-");
                }
                break;
            }
            case MONEY: {
                try {
                    data.add(moneyFormat.format(element));
                } catch (Exception e) {
                    data.add("-");
                }
                break;
            }
            case STRING: {
                if (element == null) {
                    data.add("-");
                    break;
                }
                data.add(((String) element).replace('\n', ' '));
            }
        }

        if (data.get(data.size() - 1).length() > maxDataLenght) {
            maxDataLenght = data.get(data.size() - 1).length();
        }
    }

}
