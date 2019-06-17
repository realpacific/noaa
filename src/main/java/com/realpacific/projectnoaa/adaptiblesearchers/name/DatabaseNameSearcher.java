package com.realpacific.projectnoaa.adaptiblesearchers.name;

import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.services.RecordService;

import java.util.List;

class DatabaseNameSearcher extends NameSearcher {
    private RecordService service;

    public DatabaseNameSearcher(RecordService service) {
        this.service = service;
    }

    @Override
    protected List<Record> search(String query) {
        return service.findAllRecordsByName(query);
    }

}
