package com.realpacific.projectnoaa.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class Configuration {
    private Properties properties;

    public static final String CONFIGURATION_DISPLAY_COLUMN = "app.config.display.display-columns";
    public static final String CONFIGURATION_COLUMN_WIDTH = "app.config.display.print-width";

    public Configuration(Properties properties) {
        this.properties = properties;
    }

    public Optional<Object> get(String key) {
        return Optional.ofNullable(properties.get(key));
    }

    public Map<String, String> getConfigurationToRecordNameMap() {
        Map<String, String> map = new HashMap<>();
        map.put("USAF", "usafId");
        map.put("WBAN", "wban");
        map.put("STATION NAME", "stationName");
        map.put("CTRY", "country");
        map.put("ST", "state");
        map.put("CALL", "icao");
        map.put("LAT", "latitude");
        map.put("LON", "longitude");
        map.put("ELEV(M)", "elevation");
        map.put("BEGIN", "startDate");
        map.put("END", "endDate");
        return map;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "properties=" + properties +
                '}';
    }
}
