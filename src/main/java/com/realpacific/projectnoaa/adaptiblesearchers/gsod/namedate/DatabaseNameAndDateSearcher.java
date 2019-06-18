package com.realpacific.projectnoaa.adaptiblesearchers.gsod.namedate;

import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.services.GsodService;

import java.util.List;

public class DatabaseNameAndDateSearcher extends NameAndDateSearcher {
    private GsodService service;

    public DatabaseNameAndDateSearcher(GsodService service) {
        this.service = service;
    }

    @Override
    protected List<Gsod> search(Pair<String, String> query) {
        return service.findAllGsodByNameAndDate(query.getFirst(), query.getSecond());
    }
}
