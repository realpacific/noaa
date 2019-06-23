package com.realpacific.projectnoaa.readers.filereaders;

import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.parsers.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GsodFileReader extends LocalFileReader<Gsod> {

    public GsodFileReader(File file, Parser<Map<String, Pair<Integer, Integer>>> fileHeaderParser) {
        super(file, fileHeaderParser);
    }

    @Override
    Gsod createRecord(String line) {
        Gsod.Builder builder = new Gsod.Builder();
        builder.setStationNumber(extractSubstringFromTextWithoutWhitespaces(0, line))
                .setWban(extractSubstringFromTextWithoutWhitespaces(1, line))
                .setDate(extractSubstringFromTextWithoutWhitespaces(2, line))
                .setTemperature(extractSubstringFromTextWithoutWhitespaces(3, line))
                .setDewPoint(extractSubstringFromTextWithoutWhitespaces(4, line))
                .setSeaLevelPressure(extractSubstringFromTextWithoutWhitespaces(5, line))
                .setStationPressure(extractSubstringFromTextWithoutWhitespaces(6, line))
                .setVisibility(extractSubstringFromTextWithoutWhitespaces(7, line))
                .setWindSpeed(extractSubstringFromTextWithoutWhitespaces(8, line))
                .setMaxSpeed(extractSubstringFromTextWithoutWhitespaces(9, line))
                .setGust(extractSubstringFromTextWithoutWhitespaces(10, line))
                .setMaxTemperature(extractSubstringFromTextWithoutWhitespaces(11, line))
                .setMinTemperature(extractSubstringFromTextWithoutWhitespaces(12, line))
                .setPrecipitation(extractSubstringFromTextWithoutWhitespaces(13, line))
                .setSnowDepth(extractSubstringFromTextWithoutWhitespaces(14, line))
                .setFrshtt(extractSubstringFromTextWithoutWhitespaces(15, line));
        return builder.build();
    }

    private String extractSubstringFromTextWithoutWhitespaces(int positionInTheList, String text) {
        if (isLastColumn(positionInTheList)) {
            return text.substring(getStartingValueOfColumn(positionInTheList)).trim();
        } else {
            return text.substring(getStartingValueOfColumn(positionInTheList), getEndingValueOfColumn(positionInTheList)).trim();
        }
    }


    private Integer getEndingValueOfColumn(int index) {
        return getSpacingList().get(index).getSecond();
    }

    private Integer getStartingValueOfColumn(int index) {
        return getSpacingList().get(index).getFirst();
    }
}
