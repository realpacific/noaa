package com.realpacific.projectnoaa.adaptiblesearchers.station.id;

import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Station;

import java.util.List;
import java.util.stream.Collectors;

class InMemoryIdSearcher extends IdSearcher {
    private List<Station> stations;

    public InMemoryIdSearcher(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    protected List<Station> search(Pair<String, String> query) {
        return stations.stream()
                .filter(record -> isInBetween(record.getUsafId(), query.getFirst(), query.getSecond()))
                .collect(Collectors.toList());
    }

    private boolean isInBetween(String valueToCompare, String minValue, String maxValue) {
        return valueToCompare.compareTo(minValue) >= 0 && valueToCompare.compareTo(maxValue) <= 0;
    }
}
