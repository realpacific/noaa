package com.realpacific.projectnoaa.readers.filereaders;

import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.parsers.Parser;
import com.realpacific.projectnoaa.readers.filereaders.LocalFileReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StationsFileReader extends LocalFileReader<Station> {

    public StationsFileReader(File file, Parser<Map<String, Pair<Integer, Integer>>> fileHeaderParser) {
        super(file, fileHeaderParser);
    }

    @Override
    Station createRecord(String line) {
        Station.Builder recordBuilder = new Station.Builder();
        recordBuilder.setUsafId(extractSubstringFromTextWithoutWhitespaces(0, line))
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
        if (isLastColumn(index)) {
            return text.substring(getStartingValueOfColumn(index)).trim();
        } else {
            return text.substring(getStartingValueOfColumn(index), getEndingValueOfColumn(index)).trim();
        }
    }


    private Integer getEndingValueOfColumn(int index) {
        return getSpacingList().get(index).getSecond() + 1;
    }

    private Integer getStartingValueOfColumn(int index) {
        return getSpacingList().get(index).getFirst();
    }
}
