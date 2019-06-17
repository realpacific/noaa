package com.realpacific.projectnoaa.adaptiblesearchers.country;

import com.realpacific.projectnoaa.entities.Record;

import java.util.List;
import java.util.stream.Collectors;

class InMemoryCountrySearcher extends CountrySearcher {
    private List<Record> records;

    public InMemoryCountrySearcher(List<Record> records) {
        this.records = records;
    }

    @Override
    protected List<Record> search(String query) {
        return records.stream()
                .filter(record -> record.getCountry().contains(query))
                .collect(Collectors.toList());
    }
}
