package com.realpacific.projectnoaa.adaptiblesearchers.name;

import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.readers.ConsoleReader;
import com.realpacific.projectnoaa.readers.Reader;

import java.util.List;
import java.util.stream.Collectors;

abstract class NameSearcher extends Searcher<String> {

    @Override
    protected String convert(Object query) {
        return query.toString();
    }

    @Override
    protected boolean isValid(String query) {
        return query != null && query.length() > 0;
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
