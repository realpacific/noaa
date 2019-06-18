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
    protected List<Station> search(Pair<Integer, Integer> query) {
        return stations.stream()
                .filter(record -> {
                    String stationId = extractDigitsFromUsafId(record.getUsafId());
                    int stationIdAsInteger = Integer.valueOf(stationId);
                    return (stationIdAsInteger > query.getFirst() && stationIdAsInteger < query.getSecond());
                }).collect(Collectors.toList());
    }

    private String extractDigitsFromUsafId(String usafId) {
        if (Character.isLetter(usafId.charAt(0))) {
            usafId = usafId.replaceFirst("[a-zA-Z]", "");
        }
        return usafId;
    }
}
