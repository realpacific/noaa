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
import com.realpacific.projectnoaa.readers.DummyReader;
import com.realpacific.projectnoaa.readers.GsodFileReader;
import com.realpacific.projectnoaa.readers.Reader;
import com.realpacific.projectnoaa.services.GsodService;
import com.realpacific.projectnoaa.services.imp.GsodServiceImp;
import com.realpacific.projectnoaa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GsodRunner extends Runner<Gsod> {

    private List<Gsod> gsods;
    private GsodService service = new GsodServiceImp();

    @Override
    public void run() {
        String inputPath = getFilePath();
        gsods = loadRecordsFromFile(inputPath);
        service.bulkSave(gsods);
        if (gsods.isEmpty()) System.out.println("No records present in sources.");
        else {
            performUserOperation();
        }
    }

    @Override
    List<String> getColumnNames() {
        return AppConstants.FILE_HEADERS_GSOD;
    }

    @Override
    public String getFilePath() {
        String defaultPath = "Downloads/NOA/CDO212647443607-US.gsod";
        Reader<String> reader = new DummyReader(defaultPath);
        return reader.read("Reading from path " + defaultPath);
    }

    @Override
    List<Gsod> loadRecordsFromFile(String inputPath) {
        Parser<Map<String, Pair<Integer, Integer>>> parser = new FileHeaderToColumnWidthParser(getColumnNames(), new BracketFormatter());
        Reader<List<Gsod>> textReader = new GsodFileReader(FileUtils.createFile(inputPath), parser);
        return textReader.read(null);
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
