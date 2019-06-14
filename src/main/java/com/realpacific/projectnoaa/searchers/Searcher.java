package com.realpacific.projectnoaa.searchers;

import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.readers.Reader;

import java.util.List;

public abstract class Searcher<T> {
    private List<Record> records;

    Searcher(List<Record> records) {
        this.records = records;
    }

    final List<Record> getRecords() {
        return records;
    }

    protected abstract T convert(Object query);

    protected abstract boolean isValid(T query);

    protected abstract List<Record> search(T query);

    public abstract int getNumberOfInputsRequired();

    public String[] getNamesOfInput() {
        return null;
    }

    public abstract Reader getInputReader();

    public final List<Record> process(T query) {
        System.out.println("Searching for " + query + "...");
        T formatterQuery = convert(query);
        if (!isValid(formatterQuery)) {
            throw new InvalidInputException("Query incorrect for given type");
        }
        return search(formatterQuery);
    }

}
