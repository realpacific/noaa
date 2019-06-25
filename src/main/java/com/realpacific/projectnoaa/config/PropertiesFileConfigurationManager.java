package com.realpacific.projectnoaa.config;

import java.io.InputStream;
import java.util.Properties;

class PropertiesFileConfigurationManager extends ConfigurationManager {
    private InputStream inputStream;

    PropertiesFileConfigurationManager(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    protected Properties read() {
        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
