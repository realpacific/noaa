package com.realpacific.projectnoaa.config;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class NoaaConfigurationManagerTest {
    private static ConfigurationManager reader;

    @BeforeClass
    public static void setUp() {
        reader = new PropertiesFileConfigurationManager(ConfigurationUtils.class.getClassLoader().getResourceAsStream("config.properties"));
    }

    @Test
    public void testForReader() {
        Assert.assertEquals("USAF,WBAN,STATION NAME,CTRY,ST,LAT,LON",
                reader.read().get(NoaaConfiguration.CONFIGURATION_DISPLAY_COLUMN_FOR_STATIONS).toString());
    }
}