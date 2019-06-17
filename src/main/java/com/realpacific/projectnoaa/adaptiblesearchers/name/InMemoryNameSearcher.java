package com.realpacific.projectnoaa.adaptiblesearchers.name;

import com.realpacific.projectnoaa.entities.Record;

import java.util.List;
import java.util.stream.Collectors;

class InMemoryNameSearcher extends NameSearcher {
    private List<Record> records;

    public InMemoryNameSearcher(List<Record> records) {
        this.records = records;
    }

    @Override
    protected List<Record> search(String query) {
        return records.stream()
                .filter(record -> record.getStationName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
