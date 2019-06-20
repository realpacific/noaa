package com.realpacific.projectnoaa.runner;

import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.adaptiblesearchers.StationSearchProviderFactory;
import com.realpacific.projectnoaa.config.ConfigurationManager;
import com.realpacific.projectnoaa.config.ConfigurationUtils;
import com.realpacific.projectnoaa.config.NoaaConfiguration;
import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.formatters.BracketFormatter;
import com.realpacific.projectnoaa.parsers.FileHeaderToColumnWidthParser;
import com.realpacific.projectnoaa.parsers.Parser;
import com.realpacific.projectnoaa.printers.station.StationPrinter;
import com.realpacific.projectnoaa.printers.station.TableStationPrinter;
import com.realpacific.projectnoaa.readers.ConsoleReader;
import com.realpacific.projectnoaa.readers.DummyReader;
import com.realpacific.projectnoaa.readers.Reader;
import com.realpacific.projectnoaa.readers.StationsFileReader;
import com.realpacific.projectnoaa.services.StationService;
import com.realpacific.projectnoaa.services.imp.StationServiceImp;
import com.realpacific.projectnoaa.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StationRunner extends Runner<Station> {
    private StationService service;

    public StationRunner(String workingDirectory, String archiveDirectory) {
        super(workingDirectory, archiveDirectory);
    }

    @Override
    File[] getWorkingFilesAtDirectory() {
        return FileUtils.getFilesWithNamesMatchingDescriptionAt(workingDirectory, "Stations.txt");
    }

    @Override
    List<String> getColumnNames() {
        return AppConstants.FILE_HEADERS_STATIONS;
    }

    @Override
    List<Station> loadRecordsFromFile(File... files) {
        List<Station> stationsFromFile = new ArrayList<>();
        Parser<Map<String, Pair<Integer, Integer>>> parser = new FileHeaderToColumnWidthParser(getColumnNames(), new BracketFormatter());
        for (File file : files) {
            Reader<List<Station>> textReader = new StationsFileReader(file, parser);
            stationsFromFile.addAll(textReader.read(null));
            moveFilesToArchive(file);
        }
        System.out.println("Total number of station records: " + stationsFromFile.size());
        return stationsFromFile;
    }

    @Override
    void initializeService() {
        service = new StationServiceImp();
    }

    @Override
    void persistToRepository(List<Station> data) {
        service.bulkSave(data);
    }

    @Override
    String queryUserForNatureOfOperation() {
        Reader<String> optionsReader = new ConsoleReader();
        return optionsReader.read(String.format("\n%s\n%s\n%s\n%s\n%s\nEnter operation to perform:",
                "1 - Search for station by name",
                "2 - Search stations by country code",
                "3 - Search stations by stations ID range",
                "4 - Search stations by Geographical location.",
                "5 - Exit"
        ));
    }

    @Override
    Searcher resolveUserOperation(String userInput) {
        return StationSearchProviderFactory.getSearcher(userInput, service);
    }
}
