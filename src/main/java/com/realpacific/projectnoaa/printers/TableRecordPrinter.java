package com.realpacific.projectnoaa.printers;

import com.realpacific.projectnoaa.entities.Configuration;
import com.realpacific.projectnoaa.entities.Record;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableRecordPrinter implements RecordPrinter {
    private Configuration configuration;
    private List<String> displayColumns;
    private int maxWidth;

    public TableRecordPrinter(Configuration configuration) {
        this.configuration = configuration;
        this.displayColumns = new ArrayList<>();
        configuration.get(Configuration.CONFIGURATION_DISPLAY_COLUMN)
                .ifPresent(config -> displayColumns.addAll(Arrays.asList(config.toString().split(","))));

        configuration.get(Configuration.CONFIGURATION_COLUMN_WIDTH)
                .ifPresent(configValue -> this.maxWidth = Integer.valueOf(configValue.toString()));

    }

    @Override
    public void print(List<Record> records) {
        for (String column : displayColumns) {
            createCell(column);
        }
        printHorizontalLine(displayColumns.size());

        for (Record record : records) {
            Class cls = record.getClass();
            try {
                for (String column : displayColumns) {
                    Field field = cls.getDeclaredField(configuration.getConfigurationToRecordNameMap().get(column));
                    field.setAccessible(true);
                    createCell(field.get(record).toString());
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println();
        }

    }

    private void createCell(String value) {
        System.out.format("\t%-" + maxWidth + "s\t|", value);
    }

    private void printHorizontalLine(int numberOfColumns) {
        System.out.println();
        // The number of hyphens to print accounting for space allocated for value, two tabs and a pipe symbol
        for (int i = 0; i < (numberOfColumns * (maxWidth + 4 + 4 + 1)); i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
