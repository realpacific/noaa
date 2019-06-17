package com.realpacific.projectnoaa.adaptiblesearchers.country;

import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.readers.ConsoleReader;
import com.realpacific.projectnoaa.readers.Reader;

abstract class CountrySearcher extends Searcher<String> {

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
