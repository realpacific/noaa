package com.realpacific.projectnoaa.config;

public class ConfigurationUtils {

    private static ConfigurationManager configurationManager = null;

    public static ConfigurationManager getConfigurationManager() {
        configurationManager = new PropertiesFileConfigurationManager(
                ConfigurationUtils.class.getClassLoader().getResourceAsStream("config.properties"));
        return configurationManager;
    }
}
