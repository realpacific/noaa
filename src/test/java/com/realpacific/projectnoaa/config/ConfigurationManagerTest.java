package com.realpacific.projectnoaa.config;

import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.entities.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class ConfigurationManagerTest {
    private ConfigurationManager reader;

    @Before
    public void setUp() {
        reader = new PropertiesFileManager(getClass().getClassLoader().getResourceAsStream("config.properties"));
    }

    @Test
    public void testForReader() {
        Assert.assertEquals("USAF,WBAN,STATION NAME,CTRY,ST,LAT,LON",
                reader.read().get(Configuration.CONFIGURATION_DISPLAY_COLUMN).toString());
    }
}