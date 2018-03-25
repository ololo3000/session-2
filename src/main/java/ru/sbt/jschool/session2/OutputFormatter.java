/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.sbt.jschool.session2;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 */
public class OutputFormatter {
    public enum ColumnType {
        NUMBER,
        MONEY,
        DATE,
        STRING
    }

    private PrintStream out;

    public OutputFormatter(PrintStream out) {
        this.out = out;
    }

    public class Column {
        private String title;
        private ColumnType type;
        private ArrayList<String> data;
        private int maxDataLenght = 0;
        private SimpleDateFormat dateFormat;
        private DecimalFormatSymbols symbols;
        private DecimalFormat moneyFormat;
        private DecimalFormat numberFormat;

        public Column(ColumnType type) {
            data = new ArrayList<>();
            dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator(' ');
            symbols.setDecimalSeparator(',');
            moneyFormat = new DecimalFormat("###,##0.00", symbols);
            numberFormat = new DecimalFormat("###,###", symbols);
            this.type = type;
        }

        public int getMaxDataLenght() {
            return this.maxDataLenght;
        }

        public void addTitle(String title) {
            this.title = title;
            if (title.length() > maxDataLenght) {
                maxDataLenght = title.length();
            }
        }

        public String getTitle() {
            return this.title;
        }

        public ColumnType getType() {
            return this.type;
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

        public String getElement(int index) {
            return data.get(index);
        }
    }

    public String repeatNTimes(String s, int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(s);
        }
        return builder.toString();
    }

    public String getRowSeparator(Column[] columns) {
        String s = new String();
        for (int i = 0; i < columns.length; i++) {
            s += "+";
            s += repeatNTimes("-", columns[i].maxDataLenght);
        }
        s += "+";
        return s;
    }

    public String getHeader(Column[] columns) {
        int offset = 0;
        String out = "|";
        for (int i = 0; i < columns.length; i++) {
            String title = columns[i].getTitle();
            offset = (columns[i].maxDataLenght - title.length()) / 2;
            int nameOffset = title.length() + offset;
            int sepOffset = 1 + columns[i].maxDataLenght - nameOffset;
            out += String.format("%" + nameOffset + "s%" + sepOffset + "s", title, '|');
        }
        return out;
    }


    public void output(String[] names, Object[][] data) {
        int width = names.length;
        int height = data.length;
        Column[] columns = new Column[width];

        if (names.length == 0) {
            return;
        }

        if (data.length == 0) {
            for (int i = 0; i < width; i++) {
                columns[i] = new Column(ColumnType.STRING);
                columns[i].addTitle(names[i]);
            }
        } else {
            for (int i = 0; i < width; i++) {
                if (data[0][i] instanceof Float || data[0][i] instanceof Double) {
                    columns[i] = new Column(ColumnType.MONEY);
                } else if (data[0][i] instanceof Integer) {
                    columns[i] = new Column(ColumnType.NUMBER);
                } else if (data[0][i] instanceof Date) {
                    columns[i] = new Column(ColumnType.DATE);
                } else {
                    columns[i] = new Column(ColumnType.STRING);
                }

                columns[i].addTitle(names[i]);
            }
        }

        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                columns[j].addElement(data[i][j]);
            }
        }

        String rowSeparator = getRowSeparator(columns);
        out.println(rowSeparator);
        out.println(getHeader(columns));
        out.println(rowSeparator);

        String flag = "";
        for (int i = 0; i < height; i++) {
            out.print("|");
            for (int j = 0; j < width; j++) {
                flag = "";
                if (columns[j].getType() == ColumnType.STRING) {
                    flag = "-";
                }
                out.printf("%" + flag + columns[j].maxDataLenght + "s|", columns[j].getElement(i));
            }
            out.println('\n' + rowSeparator);
        }
    }
}
