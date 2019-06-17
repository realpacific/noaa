package com.realpacific.projectnoaa.adaptiblesearchers.record.id;

import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.services.RecordService;

import java.util.List;

class DatabaseIdSearcher extends IdSearcher {
    private RecordService service;

    public DatabaseIdSearcher(RecordService service) {
        this.service = service;
    }

    @Override
    protected List<Record> search(Pair<Integer, Integer> query) {
        return service.findAllRecordsByIdRange(query.getFirst().toString(), query.getSecond().toString());
    }
}
