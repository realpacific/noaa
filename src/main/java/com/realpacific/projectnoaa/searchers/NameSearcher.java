package com.realpacific.projectnoaa.searchers;

import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.readers.ConsoleReader;
import com.realpacific.projectnoaa.readers.Reader;

import java.util.List;
import java.util.stream.Collectors;

class NameSearcher extends Searcher<String> {
    NameSearcher(List<Record> records) {
        super(records);
    }

    @Override
    protected String convert(Object query) {
        return query.toString();
    }

    @Override
    protected boolean isValid(String query) {
        return query != null && query.length() > 0;
    }

    @Override
    protected List<Record> search(String query) {
        return getRecords().stream()
                .filter(record -> record.getStationName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Reader getInputReader() {
        return new ConsoleReader();
    }

    @Override
    public int getNumberOfInputsRequired() {
        return 1;
    }

    @Override
    public String[] getNamesOfInput() {
        return new String[]{"Name"};
    }
}
