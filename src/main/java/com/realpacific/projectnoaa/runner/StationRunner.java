package com.realpacific.projectnoaa.runner;

import com.realpacific.projectnoaa.adaptiblesearchers.RecordServiceFactory;
import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.config.ConfigurationManager;
import com.realpacific.projectnoaa.config.PropertiesFileManager;
import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.config.Configuration;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.formatters.BracketFormatter;
import com.realpacific.projectnoaa.parsers.FileHeaderToColumnWidthParser;
import com.realpacific.projectnoaa.parsers.Parser;
import com.realpacific.projectnoaa.printers.record.RecordPrinter;
import com.realpacific.projectnoaa.printers.record.TableRecordPrinter;
import com.realpacific.projectnoaa.readers.ConsoleReader;
import com.realpacific.projectnoaa.readers.DummyReader;
import com.realpacific.projectnoaa.readers.Reader;
import com.realpacific.projectnoaa.readers.StationsFileReader;
import com.realpacific.projectnoaa.services.RecordService;
import com.realpacific.projectnoaa.services.imp.RecordServiceImp;
import com.realpacific.projectnoaa.utils.FileUtils;

import java.util.List;
import java.util.Map;

public class StationRunner extends Runner<Record> {
    private RecordService service = new RecordServiceImp();

    @Override
    public void run() {
        String inputPath = getFilePath();
        List<Record> records = loadRecordsFromFile(inputPath);
        if (records.isEmpty()) System.out.println("No records present in sources.");
        else {
            service.bulkSave(records);
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
    List<Record> loadRecordsFromFile(String inputPath) {
        Parser<Map<String, Pair<Integer, Integer>>> parser = new FileHeaderToColumnWidthParser(getColumnNames(), new BracketFormatter());
        Reader<List<Record>> textReader = new StationsFileReader(FileUtils.createFile(inputPath), parser);
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
        return RecordServiceFactory.getSearcher(userInput, service);
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
    void displayResult(List<Record> searchResults) {

        Configuration configuration = loadConfigurationFromFile();
        RecordPrinter printer = new TableRecordPrinter(configuration);
        printer.print(searchResults);
    }

    private Configuration loadConfigurationFromFile() {
        ConfigurationManager configurationManager =
                new PropertiesFileManager(getClass().getClassLoader().getResourceAsStream("config.properties"));
        return configurationManager.loadPropertyFile();
    }

}
