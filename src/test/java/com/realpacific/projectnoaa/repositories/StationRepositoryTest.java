package com.realpacific.projectnoaa.repositories;

import com.realpacific.projectnoaa.constants.AppConstants;
import org.junit.Test;

public class StationRepositoryTest {

    StationRepository repository = new StationRepository();

    @Test
    public void findAllRecordsByLocationRange() {
        repository.findAllStationsWithinLocationRange(0.0000, 0.0000, AppConstants.RADIUS_OF_EARTH);
    }
}