package com.realpacific.projectnoaa.runner;

import com.realpacific.projectnoaa.adaptiblesearchers.StationSearchProviderFactory;
import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.formatters.BracketFormatter;
import com.realpacific.projectnoaa.parsers.FileHeaderToColumnWidthParser;
import com.realpacific.projectnoaa.parsers.Parser;
import com.realpacific.projectnoaa.readers.ConsoleReader;
import com.realpacific.projectnoaa.readers.DummyReader;
import com.realpacific.projectnoaa.readers.StationsFileReader;
import com.realpacific.projectnoaa.readers.Reader;
import com.realpacific.projectnoaa.services.StationService;
import com.realpacific.projectnoaa.services.imp.StationServiceImp;
import com.realpacific.projectnoaa.utils.FileUtils;

import java.util.List;
import java.util.Map;

/**
 * @deprecated
 */
final public class ProjectNoaa implements ApplicationRunner {

    private StationService service = new StationServiceImp();

    @Override
    public void run() {
        launchConsoleApp();
    }

    private void launchConsoleApp() {
//        String inputPath = queryPathFromUser();
        String inputPath = loadDefaultPath();
        List<String> columnNames = AppConstants.FILE_HEADERS_STATIONS;
        List<Station> stations = readRecordsFromFile(inputPath, columnNames);
        if (stations.isEmpty()) System.out.println("No stations present in sources.");
        else {
            service.bulkSave(stations);
            queryUserForOperation();
        }
    }

    private String queryPathFromUser() {
        Reader<String> reader = new ConsoleReader();
        return reader.read("Please input the path from user's home: ~/ ");
    }

    private String loadDefaultPath() {
        String defaultPath = "Downloads/NOA/Stations.txt";
        Reader<String> reader = new DummyReader(defaultPath);
        return reader.read("Reading from path " + defaultPath);
    }

    private List<Station> readRecordsFromFile(String inputPath, List<String> columnNames) {
        Parser<Map<String, Pair<Integer, Integer>>> parser = new FileHeaderToColumnWidthParser(columnNames, new BracketFormatter());
        Reader<List<Station>> textReader = new StationsFileReader(FileUtils.getFile(inputPath), parser);
        return textReader.read(null);
    }


    private void queryUserForOperation() {
        while (true) {
            String inputOption = queryNatureOfOperation();

            Searcher searcher = StationSearchProviderFactory.getSearcher(inputOption, service);
            // Searcher searcher = StationSearchProviderFactory.getSearcher(inputOption, service.findAllStations());
            if (searcher == null) break;
            else {
                Reader searchQueryReader = searcher.getInputReader();
                Object query = searchQueryReader.read("Input Query: ");
                searcher.process(query);
            }

        }
    }

    private String queryNatureOfOperation() {
        Reader<String> optionsReader = new ConsoleReader();
        return optionsReader.read(String.format("\n%s\n%s\n%s\n%s\n%s\nEnter operation to perform:",
                "1 - Search for station by name",
                "2 - Search stations by country code",
                "3 - Search stations by stations ID range",
                "4 - Search stations by Geographical location.",
                "5 - Exit"
        ));
    }
}
