package com.realpacific.projectnoaa.services.imp;

import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.repositories.RecordRepository;
import com.realpacific.projectnoaa.services.RecordService;

import java.util.List;

public class RecordServiceImp implements RecordService {
    private RecordRepository repository = new RecordRepository();

    @Override
    public void bulkSave(List<Record> records) {
        repository.save(records);
    }

    @Override
    public List<Record> findAllRecords() {
        return repository.findAllRecords();
    }

    @Override
    public List<Record> findAllRecordsByCountry(String country) {
        return repository.findAllRecordsByCountry(country);
    }

    @Override
    public List<Record> findAllRecordsByName(String name) {
        return repository.findAllRecordsByName(name);
    }

    @Override
    public List<Record> findAllRecordsByIdRange(String begin, String end) {
        return repository.findAllRecordsByIdRange(begin, end);
    }

    @Override
    public List<Record> findAllRecordsByLocationRange(double latitude, double longitude, double radius) {
        return repository.findAllRecordsWithinLocationRange(latitude, longitude, radius);
    }
}
