package com.realpacific.projectnoaa.adaptiblesearchers.station.country;

import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.services.StationService;

import java.util.List;

class DatabaseCountrySearcher extends CountrySearcher {
    private StationService service;

    public DatabaseCountrySearcher(StationService service) {
        this.service = service;
    }

    @Override
    protected List<Station> search(String query) {
        System.out.println("hello");
        return service.findAllStationsByCountry(query);
    }

}
