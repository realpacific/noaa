package com.realpacific.projectnoaa.runner;

import com.realpacific.projectnoaa.adaptiblesearchers.StationServiceFactory;
import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.config.ConfigurationManager;
import com.realpacific.projectnoaa.config.PropertiesFileManager;
import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.config.Configuration;
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

import java.util.List;
import java.util.Map;

public class StationRunner extends Runner<Station> {
    private StationService service = new StationServiceImp();

    @Override
    public void run() {
        String inputPath = getFilePath();
        List<Station> stations = loadRecordsFromFile(inputPath);
        if (stations.isEmpty()) System.out.println("No stations present in sources.");
        else {
            service.bulkSave(stations);
            performUserOperation();
        }
    }

    @Override
    public String getFilePath() {
        String defaultPath = "Downloads/NOA/Stations.txt";
        Reader<String> reader = new DummyReader(defaultPath);
        return reader.read("Reading from path " + defaultPath);
    }

    @Override
    List<String> getColumnNames() {
        return AppConstants.FILE_HEADERS_STATIONS;
    }

    @Override
    List<Station> loadRecordsFromFile(String inputPath) {
        Parser<Map<String, Pair<Integer, Integer>>> parser = new FileHeaderToColumnWidthParser(getColumnNames(), new BracketFormatter());
        Reader<List<Station>> textReader = new StationsFileReader(FileUtils.createFile(inputPath), parser);
        return textReader.read(null);
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
        return StationServiceFactory.getSearcher(userInput, service);
    }

    @Override
    void performUserOperation() {
        while (true) {
            Searcher searcher = resolveUserOperation(queryUserForNatureOfOperation());
            if (searcher == null) break;
            else {
                Reader searchQueryReader = searcher.getInputReader();
                Object query = searchQueryReader.read("Input Query: ");
                searcher.process(query);
            }
        }
    }

    @Override
    void displayResult(List<Station> searchResults) {

        Configuration configuration = loadConfigurationFromFile();
        StationPrinter printer = new TableStationPrinter(configuration);
        printer.print(searchResults);
    }

    private Configuration loadConfigurationFromFile() {
        ConfigurationManager configurationManager =
                new PropertiesFileManager(getClass().getClassLoader().getResourceAsStream("config.properties"));
        return configurationManager.loadPropertyFile();
    }

}
