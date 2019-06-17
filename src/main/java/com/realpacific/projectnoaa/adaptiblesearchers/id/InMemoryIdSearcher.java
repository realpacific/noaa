package com.realpacific.projectnoaa.adaptiblesearchers.id;

import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Record;

import java.util.List;
import java.util.stream.Collectors;

class InMemoryIdSearcher extends IdSearcher {
    private List<Record> records;

    public InMemoryIdSearcher(List<Record> records) {
        this.records = records;
    }

    @Override
    protected List<Record> search(Pair<Integer, Integer> query) {
        return records.stream()
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
}
