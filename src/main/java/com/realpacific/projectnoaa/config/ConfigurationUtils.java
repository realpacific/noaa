package com.realpacific.projectnoaa.config;

import com.realpacific.projectnoaa.connection.HibernateUtils;

public class ConfigurationUtils {

    private static ConfigurationManager configurationManager;

    public static ConfigurationManager getConfigurationManager() {
        if (configurationManager == null) {
            configurationManager = new PropertiesFileManager(HibernateUtils.class
                    .getClassLoader().getResourceAsStream("config.properties"));
        }
        return configurationManager;
    }
}
