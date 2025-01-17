package com.realpacific.projectnoaa.printers;

import com.realpacific.projectnoaa.config.ConfigurationManager;
import com.realpacific.projectnoaa.config.ConfigurationUtils;
import com.realpacific.projectnoaa.config.NoaaConfiguration;
import com.realpacific.projectnoaa.entities.Station;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StationPrinterTest {
    private ConfigurationManager reader;

    @Before
    public void setUp() {
        reader = ConfigurationUtils.getConfigurationManager();
    }

    @Test
    public void test() {
        NoaaConfiguration configuration = reader.loadPropertyFile();
        List<String> displayColumns = new ArrayList<>();
        configuration.get(NoaaConfiguration.CONFIGURATION_DISPLAY_COLUMN_FOR_STATIONS)
                .ifPresent(config -> displayColumns.addAll(Arrays.asList(config.toString().split(","))));

        Station.Builder recordBuilder = new Station.Builder();
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

        Station station = recordBuilder.build();

        Class cls = station.getClass();
        try {
            for (String column : displayColumns) {
                Field field = cls.getDeclaredField(configuration.getConfigurationToStationNameMap().get(column));
                field.setAccessible(true);
                System.out.println(field.get(station));
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}