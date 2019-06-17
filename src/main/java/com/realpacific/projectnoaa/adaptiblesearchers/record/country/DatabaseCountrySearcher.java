package com.realpacific.projectnoaa.adaptiblesearchers.record.country;

import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.services.RecordService;

import java.util.List;

class DatabaseCountrySearcher extends CountrySearcher {
    private RecordService service;

    public DatabaseCountrySearcher(RecordService service) {
        this.service = service;
    }

    @Override
    protected List<Record> search(String query) {
        System.out.println("hello");
        return service.findAllRecordsByCountry(query);
    }

}
