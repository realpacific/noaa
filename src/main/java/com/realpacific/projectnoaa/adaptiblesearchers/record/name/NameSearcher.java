package com.realpacific.projectnoaa.adaptiblesearchers.record.name;

import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.printers.Printer;
import com.realpacific.projectnoaa.printers.record.TableRecordPrinter;
import com.realpacific.projectnoaa.readers.ConsoleReader;
import com.realpacific.projectnoaa.readers.Reader;

abstract class NameSearcher extends Searcher<String, Record> {

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

    @Override
    public Printer<Record> getPrinter() {
        return new TableRecordPrinter(loadConfigurationFromFile());
    }
}
