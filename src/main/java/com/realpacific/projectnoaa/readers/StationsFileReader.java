package com.realpacific.projectnoaa.readers;

import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.parsers.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StationsFileReader implements Reader<List<Station>> {
    private File file;
    private Parser<Map<String, Pair<Integer, Integer>>> fileHeaderParser;
    private List<Pair<Integer, Integer>> spacingList;
    private List<Station> stations = new ArrayList<>();

    public StationsFileReader(File file, Parser<Map<String, Pair<Integer, Integer>>> fileHeaderParser) {
        this.file = file;
        this.fileHeaderParser = fileHeaderParser;
    }

    @Override
    public List<Station> read(String displayMessage) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // First line contains headers
            String headerText = reader.readLine();
            spacingList = getColumnWidthInformationFromHeader(headerText);

            while ((line = reader.readLine()) != null) {
                Station station = createRecord(line);
                stations.add(station);
            }
            return stations;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new InvalidInputException("No such file found in the path.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private List<Pair<Integer, Integer>> getColumnWidthInformationFromHeader(String headerText) {
        Map<String, Pair<Integer, Integer>> headerToSpacingMap = fileHeaderParser.parse(headerText);
        return new ArrayList<>(headerToSpacingMap.values());
    }

    private Station createRecord(String line) {
        Station.Builder recordBuilder = new Station.Builder();
        recordBuilder.setUsafId(
                extractSubstringFromTextWithoutWhitespaces(0, line))
                .setWban(extractSubstringFromTextWithoutWhitespaces(1, line))
                .setStationName(extractSubstringFromTextWithoutWhitespaces(2, line))
                .setCountry(extractSubstringFromTextWithoutWhitespaces(3, line))
                .setState(extractSubstringFromTextWithoutWhitespaces(4, line))
                .setIcao(extractSubstringFromTextWithoutWhitespaces(5, line))
                .setLatitude(extractSubstringFromTextWithoutWhitespaces(6, line))
                .setLongitude(extractSubstringFromTextWithoutWhitespaces(7, line))
                .setElevation(extractSubstringFromTextWithoutWhitespaces(8, line))
                .setStartDate(extractSubstringFromTextWithoutWhitespaces(9, line))
                .setEndDate(extractSubstringFromTextWithoutWhitespaces(10, line));
        return recordBuilder.build();
    }

    private String extractSubstringFromTextWithoutWhitespaces(int index, String text) {
        if (spacingList.get(index).getSecond() == Integer.MAX_VALUE) {
            return text.substring(spacingList.get(index).getFirst()).trim();
        } else {
            return text.substring(spacingList.get(index).getFirst(), spacingList.get(index).getSecond() + 1).trim();
        }
    }
}
