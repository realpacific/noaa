package com.realpacific.projectnoaa.adaptiblesearchers.record.location;

import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.printers.Printer;
import com.realpacific.projectnoaa.printers.TableRecordPrinter;
import com.realpacific.projectnoaa.readers.MultiInputConsoleReader;
import com.realpacific.projectnoaa.readers.Reader;

import java.util.List;

abstract class LocationSearcher extends Searcher<Pair<Double, Double>, Record> {

    @Override
    protected Pair<Double, Double> convert(Object query) {
        List<String> formatterQuery = (List<String>) query;
        if (formatterQuery == null || formatterQuery.size() != getNumberOfInputsRequired()) {
            throw new InvalidInputException("Invalid input. Query requires input of  " + getNumberOfInputsRequired());
        }
        return new Pair<>(new Double(formatterQuery.get(0)), new Double(formatterQuery.get(1)));
    }

    @Override
    protected boolean isValid(Pair<Double, Double> query) {
        return query.getFirst() != null && query.getSecond() != null;
    }


    @Override
    public Reader getInputReader() {
        return new MultiInputConsoleReader(getNamesOfInput());
    }

    @Override
    public int getNumberOfInputsRequired() {
        return 2;
    }

    @Override
    public String[] getNamesOfInput() {
        return new String[]{"Latitude", "Longitude"};
    }


    @Override
    public Printer<Record> getPrinter() {
        return new TableRecordPrinter(loadConfigurationFromFile());
    }
}
