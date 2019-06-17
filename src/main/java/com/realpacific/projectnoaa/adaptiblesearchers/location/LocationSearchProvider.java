package com.realpacific.projectnoaa.adaptiblesearchers.location;

import com.realpacific.projectnoaa.adaptiblesearchers.SearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.services.RecordService;

import java.util.List;

public class LocationSearchProvider implements SearchProvider {
    @Override
    public Searcher get(RecordService service) {
        return new DatabaseLocationSearcher(service);
    }

    @Override
    public Searcher get(List<Record> records) {
        return new InMemoryLocationSearcher(records);
    }
}
