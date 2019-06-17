package com.realpacific.projectnoaa.adaptiblesearchers.record.country;

import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.readers.ConsoleReader;
import com.realpacific.projectnoaa.readers.Reader;

abstract class CountrySearcher extends Searcher<String, Record> {

    @Override
    protected String convert(Object query) {
        return query.toString();
    }

    @Override
    protected boolean isValid(String query) {
        return query != null && query.length() == 2;
    }

    @Override
    public Reader getInputReader() {
        return new ConsoleReader();
    }

    @Override
    public int getNumberOfInputsRequired() {
        return 1;
    }
}
