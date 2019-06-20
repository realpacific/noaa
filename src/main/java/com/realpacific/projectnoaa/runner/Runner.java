package com.realpacific.projectnoaa.runner;

import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.readers.Reader;
import com.realpacific.projectnoaa.utils.FileUtils;

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

    protected final void moveFilesToArchive(File file) {
        FileUtils.move(file.getPath(), archiveDirectory);
    }

    abstract void initializeService();

    public final void run() {
        while (true) {
            String option = queryUserForNatureOfOperation();
            Searcher searcher = resolveUserOperation(option);
            if (searcher == null) break;
            else {
                Reader searchQueryReader = searcher.getInputReader();
                Object query = searchQueryReader.read("Input Query: ");
                searcher.process(query);
            }
        }
    }

    abstract File[] getWorkingFilesAtDirectory();

    abstract List<String> getColumnNames();

    abstract List<T> loadRecordsFromFile(File... files);

    abstract void persistToRepository(List<T> data);

    abstract String queryUserForNatureOfOperation();

    abstract Searcher resolveUserOperation(String userInput);
}
