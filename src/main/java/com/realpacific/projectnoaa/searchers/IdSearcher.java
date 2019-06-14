package com.realpacific.projectnoaa.searchers;

import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.readers.MultiInputConsoleReader;
import com.realpacific.projectnoaa.readers.Reader;

import java.util.List;
import java.util.stream.Collectors;

class IdSearcher extends Searcher<Pair<Integer, Integer>> {
    IdSearcher(List<Record> records) {
        super(records);
    }

    @Override
    protected Pair<Integer, Integer> convert(Object query) {
        List<String> formatterQuery = (List<String>) query;
        if (formatterQuery == null || formatterQuery.size() != getNumberOfInputsRequired()) {
            throw new InvalidInputException("Invalid input. Query requires input of " + getNumberOfInputsRequired());
        }
        return new Pair<>(Integer.valueOf(formatterQuery.get(0)), Integer.valueOf(formatterQuery.get(1)));
    }

    @Override
    protected boolean isValid(Pair<Integer, Integer> query) {
        return query.getFirst() != null && query.getSecond() != null && query.getFirst() < query.getSecond();
    }

    @Override
    protected List<Record> search(Pair<Integer, Integer> query) {
        return getRecords().stream()
                .filter(record -> {
                    String stationId = extractDigitsFromUsafId(record.getUsafId());
                    int stationIdAsInteger = Integer.valueOf(stationId);
                    return (stationIdAsInteger > query.getFirst() && stationIdAsInteger < query.getSecond());
                }).collect(Collectors.toList());
    }

    private String extractDigitsFromUsafId(String usafId) {
        if (Character.isLetter(usafId.charAt(0))) {
            usafId = usafId.replaceFirst("[a-zA-Z]", "");
        }
        return usafId;
    }

    @Override
    public Reader getInputReader() {
        return new MultiInputConsoleReader(getNamesOfInput());
    }

    @Override
    public int getNumberOfInputsRequired() {
        return 2;
    }

    @Override
    public String[] getNamesOfInput() {
        return new String[]{"Min.", "Max."};
    }
}
