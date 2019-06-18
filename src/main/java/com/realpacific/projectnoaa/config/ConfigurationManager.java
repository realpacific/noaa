package com.realpacific.projectnoaa.config;

import com.sun.istack.internal.Nullable;

import java.util.Properties;

public abstract class ConfigurationManager {
    protected abstract Properties read();

    ConfigurationManager() {
    }

    @Nullable
    final public NoaaConfiguration loadPropertyFile() {
        Properties properties = read();
        if (properties == null) return null;
        return new NoaaConfiguration(properties);
    }


}
