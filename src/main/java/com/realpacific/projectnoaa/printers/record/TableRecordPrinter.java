package com.realpacific.projectnoaa.printers.record;

import com.realpacific.projectnoaa.config.Configuration;
import com.realpacific.projectnoaa.entities.Record;

import java.lang.reflect.Field;
import java.util.List;

public class TableRecordPrinter extends RecordPrinter {

    public TableRecordPrinter(Configuration configuration) {
        super(configuration);
    }

    @Override
    public void print(List<Record> records) {
        printHeaderOfTable();
        printHorizontalLine(displayColumns.size());

        for (Record record : records) {
            Class cls = record.getClass();
            try {
                for (String column : displayColumns) {
                    Field field = cls.getDeclaredField(configNameToVariableNameMap.get(column));
                    field.setAccessible(true);
                    populateSingleCell(field.get(record).toString());
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }

    private void printHeaderOfTable() {
        for (String column : displayColumns) {
            populateSingleCell(column);
        }
    }

    private void populateSingleCell(String value) {
        System.out.format("\t%-" + maxWidth + "s|", value);
    }

    private void printHorizontalLine(int numberOfColumns) {
        System.out.println();
        // The number of hyphens to print accounting for space allocated for value, tabs and a pipe symbol
        for (int i = 0; i < (numberOfColumns * (maxWidth + 4 + 1)); i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
