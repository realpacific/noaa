package com.realpacific.projectnoaa.runner;

import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.readers.DummyReader;
import com.realpacific.projectnoaa.readers.Reader;

import java.util.List;

public abstract class Runner<T> {
    public abstract void run();

    abstract List<String> getColumnNames();

    abstract List<T> loadRecordsFromFile(String inputPath);

    abstract String queryUserForNatureOfOperation();

    abstract Searcher resolveUserOperation(String userInput);

    abstract void performUserOperation();

    abstract void displayResult(List<T> searchResults);

    abstract String getFilePath();
}
