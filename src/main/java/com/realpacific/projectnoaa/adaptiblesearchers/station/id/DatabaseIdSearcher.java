package com.realpacific.projectnoaa.adaptiblesearchers.station.id;

import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.services.StationService;

import java.util.List;

class DatabaseIdSearcher extends IdSearcher {
    private StationService service;

    public DatabaseIdSearcher(StationService service) {
        this.service = service;
    }

    @Override
    protected List<Station> search(Pair<Integer, Integer> query) {
        return service.findAllStationsByIdRange(query.getFirst().toString(), query.getSecond().toString());
    }
}
