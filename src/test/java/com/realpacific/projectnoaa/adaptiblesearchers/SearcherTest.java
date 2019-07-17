package com.realpacific.projectnoaa.adaptiblesearchers;

import com.realpacific.projectnoaa.config.NoaaConfiguration;
import com.realpacific.projectnoaa.services.imp.StationServiceImp;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SearcherTest {

    private Searcher searcher;

    @Before
    public void setup() {
        searcher = StationSearchProviderFactory.getSearcher("1", new StationServiceImp());
    }

    @Test
    public void archiveAndWorkingDirectoryShouldBePresent() {
        NoaaConfiguration configuration = searcher.loadConfigurationFromFile();
        assertTrue(configuration.get(NoaaConfiguration.CONFIGURATION_WORKING_DIR).isPresent());
        assertTrue(configuration.get(NoaaConfiguration.CONFIGURATION_ARCHIVE_DIR).isPresent());
    }

}