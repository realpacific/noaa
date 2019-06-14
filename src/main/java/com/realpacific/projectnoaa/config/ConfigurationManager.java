package com.realpacific.projectnoaa.config;

import com.realpacific.projectnoaa.entities.Configuration;
import com.sun.istack.internal.Nullable;

import java.util.Properties;

public abstract class ConfigurationManager {
    protected abstract Properties read();

    @Nullable
    final public Configuration loadPropertyFile() {
        Properties properties = read();
        if (properties == null) return null;
        return new Configuration(properties);
    }


}
