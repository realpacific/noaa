package com.realpacific.projectnoaa.printers;

import com.realpacific.projectnoaa.config.Configuration;
import com.realpacific.projectnoaa.entities.Gsod;

import java.lang.reflect.Field;
import java.util.List;

public class TableGsodPrinter extends GsodPrinter {

    public TableGsodPrinter(Configuration configuration) {
        super(configuration);
    }

    @Override
    public void print(List<Gsod> data) {
        printHeaderOfTable();
        printHorizontalLine(displayColumns.size());

        for (Gsod gsod : data) {
            Class cls = gsod.getClass();
            try {
                for (String column : displayColumns) {
                    Field field = cls.getDeclaredField(this.configNameToVariableNameMap.get(column));
                    field.setAccessible(true);
                    populateSingleCell(field.get(gsod).toString());
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
