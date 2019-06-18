package com.realpacific.projectnoaa.config;

import java.io.InputStream;
import java.util.Properties;

class PropertiesFileManager extends ConfigurationManager {
    private InputStream inputStream;

    PropertiesFileManager(InputStream inputStream) {
        super();
        this.inputStream = inputStream;
    }

    @Override
    protected Properties read() {
        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (Exception ex) {
            return null;
        }
    }
}
