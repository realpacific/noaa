package com.realpacific.projectnoaa.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationManagerTest {
    private ConfigurationManager reader;

    @Before
    public void setUp() {
        reader = new PropertiesFileManager(getClass().getClassLoader().getResourceAsStream("config.properties"));
    }

    @Test
    public void testForReader() {
        Assert.assertEquals("USAF,WBAN,STATION NAME,CTRY,ST,LAT,LON",
                reader.read().get(Configuration.CONFIGURATION_DISPLAY_COLUMN_FOR_STATIONS).toString());
    }
}