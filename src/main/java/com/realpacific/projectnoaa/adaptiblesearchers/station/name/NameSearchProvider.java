package com.realpacific.projectnoaa.adaptiblesearchers.station.name;

import com.realpacific.projectnoaa.adaptiblesearchers.SearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.services.DatabaseService;
import com.realpacific.projectnoaa.services.StationService;

import java.util.List;

public class NameSearchProvider implements SearchProvider<Station> {
    @Override
    public Searcher get(DatabaseService service) {
        if (!(service instanceof StationService))
            throw new IllegalArgumentException("Invalid argument. Must be of type StationService.");
        return new DatabaseNameSearcher((StationService) service);
    }

    @Override
    public Searcher get(List<Station> stations) {
        return new InMemoryNameSearcher(stations);
    }
}