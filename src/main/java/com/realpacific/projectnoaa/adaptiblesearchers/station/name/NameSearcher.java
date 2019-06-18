package com.realpacific.projectnoaa.adaptiblesearchers.station.name;

import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.printers.Printer;
import com.realpacific.projectnoaa.printers.station.TableStationPrinter;
import com.realpacific.projectnoaa.readers.ConsoleReader;
import com.realpacific.projectnoaa.readers.Reader;

abstract class NameSearcher extends Searcher<String, Station> {

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
    public Printer<Station> getPrinter() {
        return new TableStationPrinter(loadConfigurationFromFile());
    }
}
