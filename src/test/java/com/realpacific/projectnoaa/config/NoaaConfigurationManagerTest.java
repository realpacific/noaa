package com.realpacific.projectnoaa.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NoaaConfigurationManagerTest {
    private ConfigurationManager reader;

    @Before
    public void setUp() {
        reader = new PropertiesFileManager(getClass().getClassLoader().getResourceAsStream("config.properties"));
    }

    @Test
    public void testForReader() {
        Assert.assertEquals("USAF,WBAN,STATION NAME,CTRY,ST,CALL,LAT,LON,ELEV(M),BEGIN,END",
                reader.read().get(NoaaConfiguration.CONFIGURATION_DISPLAY_COLUMN_FOR_STATIONS).toString());
    }
}