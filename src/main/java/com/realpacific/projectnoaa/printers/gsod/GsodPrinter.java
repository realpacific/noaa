package com.realpacific.projectnoaa.printers.gsod;

import com.realpacific.projectnoaa.config.NoaaConfiguration;
import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.exceptions.InvalidConfigurationException;
import com.realpacific.projectnoaa.printers.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class GsodPrinter extends Printer<Gsod> {
    List<String> displayColumns = AppConstants.FILE_HEADERS_GSOD;
    int maxWidth = 20;
    Map<String, String> configNameToVariableNameMap;


    GsodPrinter(NoaaConfiguration configuration) {
        if (configuration != null) {
            configuration.get(NoaaConfiguration.CONFIGURATION_DISPLAY_COLUMN_FOR_GSOD)
                    .ifPresent(config -> {
                        // NoaaConfiguration found! Override the defaults.
                        this.displayColumns = new ArrayList<>();
                        this.displayColumns.addAll(Arrays.asList(config.toString().split(",")));
                    });

            configNameToVariableNameMap = configuration.getConfigurationToGsodNameMap();
            checkForInvalidConfigurationColumnProperty(configNameToVariableNameMap);
        } else {
            System.out.println("Error loading display configuration. Switching to default config.");
            configNameToVariableNameMap = new NoaaConfiguration(null).getConfigurationToGsodNameMap();
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
