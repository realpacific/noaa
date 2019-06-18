package com.realpacific.projectnoaa.printers;

import com.realpacific.projectnoaa.config.ConfigurationManager;
import com.realpacific.projectnoaa.config.PropertiesFileManager;
import com.realpacific.projectnoaa.config.Configuration;
import com.realpacific.projectnoaa.entities.Record;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecordPrinterTest {
    private ConfigurationManager reader;

    @Before
    public void setUp() {
        reader = new PropertiesFileManager(getClass().getClassLoader().getResourceAsStream("config.properties"));
    }

    @Test
    public void test() {
        Configuration configuration = reader.loadPropertyFile();
        List<String> displayColumns = new ArrayList<>();
        configuration.get(Configuration.CONFIGURATION_DISPLAY_COLUMN_FOR_STATIONS)
                .ifPresent(config -> displayColumns.addAll(Arrays.asList(config.toString().split(","))));

        Record.Builder recordBuilder = new Record.Builder();
        recordBuilder.setUsafId("setUsafId value")
                .setWban("setWban value")
                .setStationName("setStationName value")
                .setCountry("setCountry value")
                .setState("setState value")
                .setIcao("setIcao value")
                .setLatitude("0.000")
                .setLongitude("0.000")
                .setElevation("66.66")
                .setStartDate("setStartDate value")
                .setEndDate("setEndDate value");

        Record record = recordBuilder.build();

        Class cls = record.getClass();
        try {
            for (String column : displayColumns) {
                Field field = cls.getDeclaredField(configuration.getConfigurationToRecordNameMap().get(column));
                field.setAccessible(true);
                System.out.println(field.get(record));
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}