package com.realpacific.projectnoaa.adaptiblesearchers.station.id;

import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

class InMemoryIdSearcher extends IdSearcher {
    private List<Station> stations;

    InMemoryIdSearcher(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    protected List<Station> search(Pair<String, String> query) {
        return stations.stream()
                .filter(record -> StringUtils.isInBetween(record.getUsafId(), query.getFirst(), query.getSecond()))
                .collect(Collectors.toList());
    }
}
