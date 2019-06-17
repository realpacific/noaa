package com.realpacific.projectnoaa.repositories;

import com.realpacific.projectnoaa.constants.AppConstants;
import org.junit.Test;

public class RecordRepositoryTest {

    RecordRepository repository = new RecordRepository();

    @Test
    public void findAllRecordsByLocationRange() {
        repository.findAllRecordsWithinLocationRange(0.0000, 0.0000, AppConstants.RADIUS_OF_EARTH);
    }
}