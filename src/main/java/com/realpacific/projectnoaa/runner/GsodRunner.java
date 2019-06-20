package com.realpacific.projectnoaa.runner;

import com.realpacific.projectnoaa.adaptiblesearchers.GsodSearchProviderFactory;
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
import java.util.List;
import java.util.Map;

public class GsodRunner extends Runner<Gsod> {

    private GsodService service;

    public GsodRunner(String workingDirectory, String archiveDirectory) {
        super(workingDirectory, archiveDirectory);
    }

    @Override
    File[] getWorkingFilesAtDirectory() {
        return FileUtils.getFilesWithNamesMatchingDescriptionAt(workingDirectory, "*.gsod");
    }

    @Override
    List<Gsod> loadRecordsFromFile(File... files) {
        Parser<Map<String, Pair<Integer, Integer>>> parser = new FileHeaderToColumnWidthParser(getColumnNames(), new BracketFormatter());
        List<Gsod> gsodsFromFile = new ArrayList<>();
        for (File file : files) {
            Reader<List<Gsod>> textReader = new GsodFileReader(file, parser);
            gsodsFromFile.addAll(textReader.read(null));
            moveFilesToArchive(file);
        }
        System.out.println("Total number of gsod records: " + gsodsFromFile.size());
        return gsodsFromFile;
    }

    @Override
    void initializeService() {
        service = new GsodServiceImp();
    }

    @Override
    void persistToRepository(List<Gsod> data) {
        service.bulkSave(data);
    }

    @Override
    List<String> getColumnNames() {
        return AppConstants.FILE_HEADERS_GSOD;
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
        return GsodSearchProviderFactory.getSearcher(userInput, service);
    }
}
