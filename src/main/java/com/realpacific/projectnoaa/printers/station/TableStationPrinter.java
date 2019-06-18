package com.realpacific.projectnoaa.printers.station;

import com.realpacific.projectnoaa.config.NoaaConfiguration;
import com.realpacific.projectnoaa.entities.Station;

import java.lang.reflect.Field;
import java.util.List;

public class TableStationPrinter extends StationPrinter {

    public TableStationPrinter(NoaaConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void print(List<Station> stations) {
        printHeaderOfTable();
        printHorizontalLine(displayColumns.size());

        for (Station station : stations) {
            Class cls = station.getClass();
            try {
                for (String column : displayColumns) {
                    Field field = cls.getDeclaredField(configNameToVariableNameMap.get(column));
                    field.setAccessible(true);
                    populateSingleCell(field.get(station).toString());
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
