package com.realpacific.projectnoaa.adaptiblesearchers.station.id;

import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.printers.Printer;
import com.realpacific.projectnoaa.printers.station.TableStationPrinter;
import com.realpacific.projectnoaa.readers.MultiInputConsoleReader;
import com.realpacific.projectnoaa.readers.Reader;

import java.util.List;

abstract class IdSearcher extends Searcher<Pair<String, String>, Station> {
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
        return query.getFirst() != null && query.getSecond() != null && query.getFirst().compareTo(query.getSecond()) < 0;
    }


    private String extractDigitsFromUsafId(String usafId) {
        if (Character.isLetter(usafId.charAt(0))) {
            usafId = usafId.replaceFirst("[a-zA-Z]", "");
        }
        return usafId;
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
        return new String[]{"Min.", "Max."};
    }


    @Override
    public Printer<Station> getPrinter() {
        return new TableStationPrinter(loadConfigurationFromFile());
    }
}
