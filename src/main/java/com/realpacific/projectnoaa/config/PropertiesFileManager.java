package com.realpacific.projectnoaa.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileManager extends ConfigurationManager {
    private InputStream inputStream;

    public PropertiesFileManager(InputStream inputStream) {
        super();
        this.inputStream = inputStream;
    }

    @Override
    protected Properties read() {
        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException ex) {
            return null;
        }
    }
}
