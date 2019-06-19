package com.realpacific.projectnoaa.runner;

import com.realpacific.projectnoaa.adaptiblesearchers.GsodServiceFactory;
import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.formatters.BracketFormatter;
import com.realpacific.projectnoaa.parsers.FileHeaderToColumnWidthParser;
import com.realpacific.projectnoaa.parsers.Parser;
import com.realpacific.projectnoaa.readers.ConsoleReader;
import com.realpacific.projectnoaa.readers.GsodFileReader;
import com.realpacific.projectnoaa.readers.Reader;
import com.realpacific.projectnoaa.services.GsodService;
import com.realpacific.projectnoaa.services.imp.GsodServiceImp;
import com.realpacific.projectnoaa.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GsodRunner extends Runner<Gsod> {

    private GsodService service = new GsodServiceImp();
    private String workingDirectory;
    private String archiveDirectory;

    public GsodRunner(String workingDirectory, String archiveDirectory) {
        this.workingDirectory = workingDirectory;
        this.archiveDirectory = archiveDirectory;
        File[] filesToRead = FileUtils.getFilesAt(workingDirectory, "gsod");
        if (filesToRead.length > 0) {
            List<Gsod> gsods = loadRecordsFromFile(filesToRead);
            service.bulkSave(gsods);
        }
    }

    @Override
    public void run() {
        performUserOperation();
    }

    @Override
    List<String> getColumnNames() {
        return AppConstants.FILE_HEADERS_GSOD;
    }

    @Override
    public String getWorkingDirectory() {
        return this.workingDirectory;
    }

    @Override
    List<Gsod> loadRecordsFromFile(File... files) {
        Parser<Map<String, Pair<Integer, Integer>>> parser = new FileHeaderToColumnWidthParser(getColumnNames(), new BracketFormatter());
        List<Gsod> gsodsFromFile = new ArrayList<>();
        for (File file : files) {
            Reader<List<Gsod>> textReader = new GsodFileReader(file, parser);
            gsodsFromFile.addAll(textReader.read(null));
            FileUtils.move(file.toString(), archiveDirectory);
        }
        System.out.println("Total number of gsod records: " + gsodsFromFile.size());
        return gsodsFromFile;
    }

    @Override
    String queryUserForNatureOfOperation() {
        Reader<String> optionsReader = new ConsoleReader();
        return optionsReader.read(String.format("\n%s\n%s\n%s\n%s\n%s\nEnter operation to perform:",
                "1- Display available dates",
                "2- Display summary by station ID and date",
                "3- Display summaries by country and date",
                "4- Display summary by station name and date.",
                "5- Exit"
        ));
    }

    @Override
    Searcher resolveUserOperation(String userInput) {
        return GsodServiceFactory.getSearcher(userInput, service);
    }

    @Override
    void performUserOperation() {
        while (true) {
            String userInput = queryUserForNatureOfOperation();
            Searcher searcher = resolveUserOperation(userInput);
            if (searcher == null) break;
            else {
                Reader searchQueryReader = searcher.getInputReader();
                Object query = searchQueryReader.read("Input Query: ");
                searcher.process(query);
            }
        }
    }

    @Override
    void displayResult(List<Gsod> searchResults) {
        for (Gsod gsod : searchResults) {
            System.out.println(gsod);
        }
    }
}
