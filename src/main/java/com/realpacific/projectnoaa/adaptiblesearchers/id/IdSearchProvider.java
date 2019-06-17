package com.realpacific.projectnoaa.adaptiblesearchers.id;

import com.realpacific.projectnoaa.adaptiblesearchers.SearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.services.RecordService;

import java.util.List;

public class IdSearchProvider implements SearchProvider {
    @Override
    public Searcher get(RecordService service) {
        return new DatabaseIdSearcher(service);
    }

    @Override
    public Searcher get(List<Record> records) {
        return new InMemoryIdSearcher(records);
    }
}
