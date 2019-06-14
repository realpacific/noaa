package com.realpacific.projectnoaa.runner;

import com.realpacific.projectnoaa.config.ConfigurationManager;
import com.realpacific.projectnoaa.config.PropertiesFileManager;
import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.entities.Configuration;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.parsers.FileHeaderParser;
import com.realpacific.projectnoaa.parsers.Parser;
import com.realpacific.projectnoaa.printers.TableRecordPrinter;
import com.realpacific.projectnoaa.printers.RecordPrinter;
import com.realpacific.projectnoaa.readers.ConsoleReader;
import com.realpacific.projectnoaa.readers.DummyReader;
import com.realpacific.projectnoaa.readers.LocalFileReader;
import com.realpacific.projectnoaa.readers.Reader;
import com.realpacific.projectnoaa.formatter.BracketFormatter;
import com.realpacific.projectnoaa.searchers.Searcher;
import com.realpacific.projectnoaa.searchers.SearcherFactory;
import com.realpacific.projectnoaa.utils.FileUtils;

import java.util.*;

final public class ProjectNoaa implements ApplicationRunner {

    @Override
    public void run(String... arguments) {
        launchConsoleApp();
    }

    private void launchConsoleApp() {
        String inputPath = queryPathFromUser();
//        String inputPath = loadDefaultPath();
        List<Record> records = readRecordsFromFile(inputPath);
        if (records.isEmpty()) System.out.println("No records present in sources.");
        else {
            queryUserForOperation(records);
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

    private List<Record> readRecordsFromFile(String inputPath) {
        Parser<Map<String, Pair<Integer, Integer>>> parser = new FileHeaderParser(AppConstants.FILE_HEADERS, new BracketFormatter());
        Reader<List<Record>> textReader = new LocalFileReader(FileUtils.createFile(inputPath), parser);
        return textReader.read(null);
    }


    private void queryUserForOperation(List<Record> records) {
        while (true) {
            String inputOption = queryNatureOfOperation();

            Searcher searcher = SearcherFactory.getSearcher(inputOption, records);
            List<Record> searchResults = new ArrayList<>();
            if (searcher == null) break;
            else {
                Reader searchQueryReader = searcher.getInputReader();
                Object query = searchQueryReader.read("Input Query: ");
                searchResults.addAll(searcher.process(query));
            }
            displayResult(searchResults);
        }
    }

    private String queryNatureOfOperation() {
        Reader<String> optionsReader = new ConsoleReader();
        return optionsReader.read(String.format("\n%s\n%s\n%s\n%s\n%s\nEnter operation to perform:",
                "1 - Search for station by name",
                "2- Search stations by country code",
                "3- Search stations by stations ID range",
                "4- Search stations by Geographical location.",
                "5- Exit"
        ));
    }

    private void displayResult(List<Record> searchResults) {
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
