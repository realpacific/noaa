package com.realpacific.projectnoaa.adaptiblesearchers.station.country;

import com.realpacific.projectnoaa.entities.Station;

import java.util.List;
import java.util.stream.Collectors;

class InMemoryCountrySearcher extends CountrySearcher {
    private List<Station> stations;

    public InMemoryCountrySearcher(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    protected List<Station> search(String query) {
        return stations.stream()
                .filter(record -> record.getCountry().contains(query))
                .collect(Collectors.toList());
    }
}
