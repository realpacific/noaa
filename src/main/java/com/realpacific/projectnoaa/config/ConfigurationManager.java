package com.realpacific.projectnoaa.config;

import com.realpacific.projectnoaa.entities.Configuration;

import java.util.Properties;

public abstract class ConfigurationManager {
    protected abstract Properties read();

    final public Configuration loadPropertyFile() {
        Properties properties = read();
        return new Configuration(properties);
    }


}
