package ru.sbt.jschool.session2;

public class CutStrFormatter implements Formatting {

    int maxStrLen = 0;

    public CutStrFormatter(int maxStrLen) {
        this.maxStrLen = maxStrLen;
    }

    @Override
    public String format(Object object) {
        String formattedObj;
        if (object != null) {
            formattedObj = ((String) object).replace('\n', ' ');
        } else {
            formattedObj = "-";
        }
        if (formattedObj.length() > maxStrLen) {
            formattedObj = formattedObj.substring(0, maxStrLen - 3) + "...";
        }
        return formattedObj;
    }
}
