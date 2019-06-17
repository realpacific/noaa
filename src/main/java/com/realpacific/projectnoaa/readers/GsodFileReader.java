package com.realpacific.projectnoaa.readers;

import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.parsers.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GsodFileReader implements Reader<List<Gsod>> {
    private File file;
    private Parser<Map<String, Pair<Integer, Integer>>> fileHeaderParser;
    private List<Pair<Integer, Integer>> spacingList;
    private List<Gsod> gsods = new ArrayList<>();

    public GsodFileReader(File file, Parser<Map<String, Pair<Integer, Integer>>> fileHeaderParser) {
        this.file = file;
        this.fileHeaderParser = fileHeaderParser;
    }

    @Override
    public List<Gsod> read(String displayMessage) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // First line contains headers
            String headerText = reader.readLine();
            spacingList = getColumnWidthInformationFromHeader(headerText);

            while ((line = reader.readLine()) != null) {
                Gsod gsod = createGsod(line);
                gsods.add(gsod);
            }
            return gsods;
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


    private Gsod createGsod(String line) {
        Gsod.Builder builder = new Gsod.Builder();
        builder.setStationNumber(extractSubstringFromTextWithoutWhitespaces(0, line));
        builder.setWban(extractSubstringFromTextWithoutWhitespaces(1, line));
        builder.setDate(extractSubstringFromTextWithoutWhitespaces(2, line));
        builder.setTemperature(extractSubstringFromTextWithoutWhitespaces(3, line));
        builder.setDewPoint(extractSubstringFromTextWithoutWhitespaces(4, line));
        builder.setSeaLevelPressure(extractSubstringFromTextWithoutWhitespaces(5, line));
        builder.setStationPressure(extractSubstringFromTextWithoutWhitespaces(6, line));
        builder.setVisibility(extractSubstringFromTextWithoutWhitespaces(7, line));
        builder.setWindSpeed(extractSubstringFromTextWithoutWhitespaces(8, line));
        builder.setMaxSpeed(extractSubstringFromTextWithoutWhitespaces(9, line));
        builder.setGust(extractSubstringFromTextWithoutWhitespaces(10, line));
        builder.setMaxTemperature(extractSubstringFromTextWithoutWhitespaces(11, line));
        builder.setMinTemperature(extractSubstringFromTextWithoutWhitespaces(12, line));
        builder.setPrecipitation(extractSubstringFromTextWithoutWhitespaces(13, line));
        builder.setSnowDepth(extractSubstringFromTextWithoutWhitespaces(14, line));
        builder.setFrshtt(extractSubstringFromTextWithoutWhitespaces(15, line));
        return builder.build();
    }

    private String extractSubstringFromTextWithoutWhitespaces(int index, String text) {
        if (spacingList.get(index).getSecond() == Integer.MAX_VALUE) {
            return text.substring(spacingList.get(index).getFirst()).trim();
        } else {
            return text.substring(spacingList.get(index).getFirst(), spacingList.get(index).getSecond()).trim();
        }
    }
}
