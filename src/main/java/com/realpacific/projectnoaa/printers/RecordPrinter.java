package com.realpacific.projectnoaa.printers;

import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.entities.Configuration;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.exceptions.InvalidConfigurationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class RecordPrinter {
    List<String> displayColumns = AppConstants.FILE_HEADERS;
    int maxWidth = 30;
    Map<String, String> configNameToVariableNameMap;


    RecordPrinter(Configuration configuration) {
        configuration.get(Configuration.CONFIGURATION_DISPLAY_COLUMN)
                .ifPresent(config -> {
                    this.displayColumns = new ArrayList<>();
                    this.displayColumns.addAll(Arrays.asList(config.toString().split(",")));
                });

        configuration.get(Configuration.CONFIGURATION_COLUMN_WIDTH)
                .ifPresent(configValue -> this.maxWidth = Integer.valueOf(configValue.toString()));

        configNameToVariableNameMap = configuration.getConfigurationToRecordNameMap();
        checkForInvalidConfigurationColumnProperty(configNameToVariableNameMap);
    }

    public abstract void print(List<Record> records);


    private void checkForInvalidConfigurationColumnProperty(Map<String, String> configNameToVariableNameMap) {
        for (String column : displayColumns) {
            if (!configNameToVariableNameMap.keySet().contains(column))
                throw new InvalidConfigurationException("Invalid configuration file property. Should have one or more of: " +
                        configNameToVariableNameMap.keySet().toString());
        }
    }
}
