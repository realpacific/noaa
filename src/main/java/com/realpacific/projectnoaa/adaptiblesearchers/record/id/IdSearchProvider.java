package com.realpacific.projectnoaa.adaptiblesearchers.record.id;

import com.realpacific.projectnoaa.adaptiblesearchers.SearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.services.DatabaseService;
import com.realpacific.projectnoaa.services.RecordService;

import java.util.List;

public class IdSearchProvider implements SearchProvider<Record> {
    @Override
    public Searcher get(DatabaseService service) {
        if (!(service instanceof RecordService))
            throw new IllegalArgumentException("Invalid argument. Must be of type RecordService.");
        return new DatabaseIdSearcher((RecordService) service);
    }

    @Override
    public Searcher get(List<Record> records) {
        return new InMemoryIdSearcher(records);
    }
}
