package com.realpacific.projectnoaa.adaptiblesearchers.gsod.iddate;

import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.printers.Printer;
import com.realpacific.projectnoaa.printers.gsod.TableGsodPrinter;
import com.realpacific.projectnoaa.readers.MultiInputConsoleReader;
import com.realpacific.projectnoaa.readers.Reader;

import java.util.List;

abstract class IdAndDateSearcher extends Searcher<Pair<String, String>, Gsod> {

    @Override
    protected Pair<String, String> convert(Object query) {
        List<String> formatterQuery = (List<String>) query;
        if (formatterQuery == null || formatterQuery.size() != getNumberOfInputsRequired()) {
            throw new InvalidInputException("Invalid input. Query requires input of  " + getNumberOfInputsRequired());
        }
        return new Pair<>(formatterQuery.get(0), formatterQuery.get(1));
    }

    @Override
    protected boolean isValid(Pair<String, String> query) {
        return query.getFirst() != null && query.getSecond() != null && !query.getFirst().isEmpty() && !query.getSecond().isEmpty();
    }


    @Override
    public int getNumberOfInputsRequired() {
        return 2;
    }

    @Override
    public Reader getInputReader() {
        return new MultiInputConsoleReader("Id", "Date (YYYYMMDD)");
    }

    @Override
    public Printer<Gsod> getPrinter() {
        return new TableGsodPrinter(loadConfigurationFromFile());
    }
}
