package ru.sbt.jschool.session2;

import java.util.ArrayList;

public class ColumnFormatter {
    private String title;
    private Formatting dataFormatter;
    private ArrayList<String> data = new ArrayList<>();
    ;
    private int maxDataLength = 0;

    public ColumnFormatter(Formatting dataFormatter) {
        this.dataFormatter = dataFormatter;
    }

    public int getMaxDataLength() {
        return this.maxDataLength;
    }

    public String getTitle() {
        return this.title;
    }

    public Formatting getDataFormatter() {
        return this.dataFormatter;
    }

    public void addTitle(String title) {
        this.title = title;
        if (title.length() > maxDataLength) {
            maxDataLength = title.length();
        }
    }

    public String getElement(int index) {
        return data.get(index);
    }

    public void addElement(Object element) {
        String formattedElement;
        if (dataFormatter != null) {
            formattedElement = dataFormatter.format(element);
            data.add(formattedElement);
        } else {
            formattedElement = element.toString();
        }

        if (formattedElement.length() > maxDataLength) {
            maxDataLength = data.get(data.size() - 1).length();
        }
    }
}
