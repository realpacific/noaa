package com.realpacific.projectnoaa.printers.singlevalue;

import com.realpacific.projectnoaa.printers.Printer;

import java.util.List;

public class SingleValuePrinter extends Printer<String> {
    private String columnName;
    private int maxWidth = 30;

    public SingleValuePrinter(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public void print(List<String> records) {
        populateSingleCell(columnName);
        printHorizontalLine();

        for (String record : records) {
            populateSingleCell(record);
            printHorizontalLine();
        }
    }


    private void populateSingleCell(String value) {
        System.out.format("\t%-" + maxWidth + "s|", value);
    }

    private void printHorizontalLine() {
        System.out.println();
        // Compensate for the max width and the tab in a cell
        for (int i = 0; i < (maxWidth + 4); i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
