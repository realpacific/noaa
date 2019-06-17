package com.realpacific.projectnoaa.adaptiblesearchers;

import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.readers.Reader;

import java.util.List;

public abstract class Searcher<T, R> {
    protected abstract T convert(Object query);

    protected abstract boolean isValid(T query);

    protected abstract List<R> search(T query);

    public abstract int getNumberOfInputsRequired();

    public String[] getNamesOfInput() {
        return null;
    }

    public abstract Reader getInputReader();

    public final List<R> process(T query) {
        System.out.println("Searching for " + query + "...");
        T formatterQuery = convert(query);
        if (!isValid(formatterQuery)) {
            throw new InvalidInputException("Query incorrect for given type");
        }
        return search(formatterQuery);
    }

}
