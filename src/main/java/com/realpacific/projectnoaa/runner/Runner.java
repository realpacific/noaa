package com.realpacific.projectnoaa.runner;

import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;

import java.io.File;
import java.util.List;

public abstract class Runner<T> {
    protected String workingDirectory;
    protected String archiveDirectory;

    protected Runner(String workingDirectory, String archiveDirectory) {
        this.workingDirectory = workingDirectory;
        this.archiveDirectory = archiveDirectory;

        File[] filesToRead = getWorkingFilesAtDirectory();
        List<T> data = loadRecordsFromFile(filesToRead);
        initializeService();
        if (data.size() > 0) {
            persistToRepository(data);
        }
    }

    abstract void initializeService();

    public abstract void run();

    abstract File[] getWorkingFilesAtDirectory();

    abstract List<String> getColumnNames();

    abstract List<T> loadRecordsFromFile(File... files);

    abstract void persistToRepository(List<T> data);

    abstract String queryUserForNatureOfOperation();

    abstract Searcher resolveUserOperation(String userInput);

    abstract void performUserOperation();
}
