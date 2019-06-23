package com.realpacific.projectnoaa.readers.filereaders;

import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.parsers.Parser;
import com.realpacific.projectnoaa.readers.Reader;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class LocalFileReader<T> implements Reader<List<T>> {
    private File file;
    private Parser<Map<String, Pair<Integer, Integer>>> fileHeaderParser;
    private List<Pair<Integer, Integer>> spacingList;
    private List<T> records = new ArrayList<>();

    LocalFileReader(File file, Parser<Map<String, Pair<Integer, Integer>>> fileHeaderParser) {
        this.file = file;
        this.fileHeaderParser = fileHeaderParser;
    }

    List<Pair<Integer, Integer>> getSpacingList() {
        return spacingList;
    }

    @Override
    public List<T> read(String displayMessage) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // First line contains headers
            String headerText = reader.readLine();
            spacingList = getColumnWidthInformationFromHeader(headerText);

            while ((line = reader.readLine()) != null) {
                T record = createRecord(line);
                records.add(record);
            }
            return records;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new InvalidInputException("No such file found in the path.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    protected final boolean isLastColumn(int index) {
        return getSpacingList().get(index).getSecond() == Integer.MAX_VALUE;
    }

    private List<Pair<Integer, Integer>> getColumnWidthInformationFromHeader(String headerText) {
        Map<String, Pair<Integer, Integer>> headerToSpacingMap = fileHeaderParser.parse(headerText);
        return new ArrayList<>(headerToSpacingMap.values());
    }

    abstract T createRecord(String line);


}
