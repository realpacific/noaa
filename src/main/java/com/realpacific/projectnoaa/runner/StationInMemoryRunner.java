package com.realpacific.projectnoaa.runner;

import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.adaptiblesearchers.StationSearchProviderFactory;
import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.formatters.BracketFormatter;
import com.realpacific.projectnoaa.parsers.FileHeaderToColumnWidthParser;
import com.realpacific.projectnoaa.parsers.Parser;
import com.realpacific.projectnoaa.readers.ConsoleReader;
import com.realpacific.projectnoaa.readers.Reader;
import com.realpacific.projectnoaa.readers.filereaders.StationsFileReader;
import com.realpacific.projectnoaa.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StationInMemoryRunner extends Runner<Station> {
    private List<Station> stations;

    public StationInMemoryRunner() {
        super(null, null);
    }

    @Override
    void initializeService() {
        stations = new ArrayList<>();
    }

    @Override
    File[] getWorkingFilesAtDirectory() {
        Reader<String> reader = new ConsoleReader();
        String inputPath = reader.read("Please input the path from user's home to the Stations.txt file: ~/");
        return FileUtils.getFilesWithNamesMatchingDescriptionAt(inputPath, "Stations.txt");
    }

    @Override
    List<String> getColumnNames() {
        return AppConstants.FILE_HEADERS_STATIONS;
    }

    @Override
    List<Station> loadRecordsFromFile(File... files) {
        List<Station> stationsReadFromFile = new ArrayList<>();
        for (File file : files) {
            Parser<Map<String, Pair<Integer, Integer>>> parser = new FileHeaderToColumnWidthParser(getColumnNames(), new BracketFormatter());
            Reader<List<Station>> textReader = new StationsFileReader(file, parser);
            stationsReadFromFile.addAll(textReader.read(null));
        }
        return stationsReadFromFile;
    }

    @Override
    void persistToRepository(List<Station> data) {
        stations.addAll(data);
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
        return StationSearchProviderFactory.getSearcher(userInput, stations);
    }
}
