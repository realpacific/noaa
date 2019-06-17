package com.realpacific.projectnoaa.adaptiblesearchers.location;

import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.services.RecordService;

import java.util.List;

class DatabaseLocationSearcher extends LocationSearcher {
    private RecordService service;

    public DatabaseLocationSearcher(RecordService service) {
        this.service = service;
    }

    @Override
    protected List<Record> search(Pair<Double, Double> query) {
        return service.findAllRecordsByLocationRange(query.getFirst(), query.getSecond(), AppConstants.RADIUS_OF_EARTH);
    }
}
