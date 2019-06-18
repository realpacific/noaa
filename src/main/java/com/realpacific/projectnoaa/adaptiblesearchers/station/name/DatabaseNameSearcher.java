package com.realpacific.projectnoaa.adaptiblesearchers.station.name;

import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.services.StationService;

import java.util.List;

class DatabaseNameSearcher extends NameSearcher {
    private StationService service;

    public DatabaseNameSearcher(StationService service) {
        this.service = service;
    }

    @Override
    protected List<Station> search(String query) {
        return service.findAllStationsByName(query);
    }

}
