package com.realpacific.projectnoaa.adaptiblesearchers.gsod.iddate;

import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Pair;

import java.util.List;
import java.util.stream.Collectors;

class InMemoryIdAndDateSearcher extends IdAndDateSearcher {
    private List<Gsod> gsods;

    InMemoryIdAndDateSearcher(List<Gsod> gsods) {
        this.gsods = gsods;
    }

    @Override
    protected List<Gsod> search(Pair<String, String> query) {
        return this.gsods.stream()
                .filter(gsod -> query.getFirst().equals(gsod.getStationNumber()) && query.getSecond().equals(gsod.getDate()))
                .collect(Collectors.toList());
    }
}
