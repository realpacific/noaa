package com.realpacific.projectnoaa.adaptiblesearchers.gsod.date;

import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.printers.Printer;
import com.realpacific.projectnoaa.printers.TableGsodPrinter;
import com.realpacific.projectnoaa.readers.DummyReader;
import com.realpacific.projectnoaa.readers.MultiInputConsoleReader;
import com.realpacific.projectnoaa.readers.Reader;

import java.util.List;

abstract class  AvailableDateSearcher extends Searcher<Pair<String, String>, String> {

    @Override
    protected Pair<String, String> convert(Object query) {
        return null;
    }

    @Override
    protected boolean isValid(Pair<String, String> query) {
        return true;
    }


    @Override
    public int getNumberOfInputsRequired() {
        return 0;
    }

    @Override
    public Reader getInputReader() {
        return new DummyReader(null);
    }


    @Override
    public Printer<String> getPrinter() {
        // TODO OVerride here
        return null;
    }
}
