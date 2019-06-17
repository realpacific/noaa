package com.realpacific.projectnoaa.services;

import com.realpacific.projectnoaa.entities.Record;

import java.util.List;

public interface RecordService {
    void bulkSave(List<Record> records);

    List<Record> findAllRecords();

    List<Record> findAllRecordsByCountry(String country);
    List<Record> findAllRecordsByName(String name);
    List<Record> findAllRecordsByIdRange(String begin, String end);
    List<Record> findAllRecordsByLocationRange(double latitude, double longitude, double radius);
}
