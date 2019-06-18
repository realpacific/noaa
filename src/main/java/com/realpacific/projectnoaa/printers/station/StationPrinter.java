package com.realpacific.projectnoaa.printers.station;

import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.config.Configuration;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.exceptions.InvalidConfigurationException;
import com.realpacific.projectnoaa.printers.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class StationPrinter extends Printer<Station> {
    List<String> displayColumns = AppConstants.FILE_HEADERS_STATIONS;
    int maxWidth = 30;
    Map<String, String> configNameToVariableNameMap;


    StationPrinter(Configuration configuration) {
        if (configuration != null) {
            configuration.get(Configuration.CONFIGURATION_DISPLAY_COLUMN_FOR_STATIONS)
                    .ifPresent(config -> {
                        // Configuration found! Override the defaults.
                        this.displayColumns = new ArrayList<>();
                        this.displayColumns.addAll(Arrays.asList(config.toString().split(",")));
                    });

            configuration.get(Configuration.CONFIGURATION_COLUMN_WIDTH)
                    .ifPresent(configValue -> this.maxWidth = Integer.valueOf(configValue.toString()));

            configNameToVariableNameMap = configuration.getConfigurationToRecordNameMap();
            checkForInvalidConfigurationColumnProperty(configNameToVariableNameMap);
        } else {
            System.out.println("Error loading display configuration. Switching to default config.");
            configNameToVariableNameMap = new Configuration(null).getConfigurationToRecordNameMap();
        }
    }

    private void checkForInvalidConfigurationColumnProperty(Map<String, String> configNameToVariableNameMap) {
        for (String column : displayColumns) {
            if (!configNameToVariableNameMap.keySet().contains(column))
                throw new InvalidConfigurationException("Invalid configuration file property. Should have one or more of: " +
                        configNameToVariableNameMap.keySet().toString());
        }
    }
}
