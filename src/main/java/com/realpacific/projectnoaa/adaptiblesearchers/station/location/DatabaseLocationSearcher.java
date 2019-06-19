package com.realpacific.projectnoaa.adaptiblesearchers.station.location;

import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.services.StationService;

import java.util.List;

class DatabaseLocationSearcher extends LocationSearcher {
    private StationService service;

    DatabaseLocationSearcher(StationService service) {
        this.service = service;
    }

    @Override
    protected List<Station> search(Pair<Double, Double> query) {
        return service.findAllStationsByLocationRange(query.getFirst(), query.getSecond(), AppConstants.RADIUS_OF_EARTH);
    }
}
