package com.realpacific.projectnoaa.adaptiblesearchers.country;

import com.realpacific.projectnoaa.adaptiblesearchers.SearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.services.RecordService;

import java.util.List;

public class CountrySearchProvider implements SearchProvider {

    @Override
    public Searcher<String> get(RecordService service) {
        return new DatabaseCountrySearcher(service);
    }

    @Override
    public Searcher get(List<Record> records) {
        return new InMemoryCountrySearcher(records);
    }
}
