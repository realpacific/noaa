package com.realpacific.projectnoaa.adaptiblesearchers.station.name;

import com.realpacific.projectnoaa.entities.Station;

import java.util.List;
import java.util.stream.Collectors;

class InMemoryNameSearcher extends NameSearcher {
    private List<Station> stations;

    InMemoryNameSearcher(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    protected List<Station> search(String query) {
        return stations.stream()
                .filter(record -> record.getStationName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
