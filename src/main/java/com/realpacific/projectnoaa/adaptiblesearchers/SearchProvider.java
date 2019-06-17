package com.realpacific.projectnoaa.adaptiblesearchers;

import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.services.RecordService;

import java.util.List;

public interface SearchProvider {
    public Searcher get(RecordService service);

    public Searcher get(List<Record> records);
}
