package com.realpacific.projectnoaa.adaptiblesearchers.gsod.iddate;

import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.services.GsodService;

import java.util.List;

public class DatabaseIdAndDateSearcher extends IdAndDateSearcher {
    private GsodService service;

    public DatabaseIdAndDateSearcher(GsodService service) {
        this.service = service;
    }

    @Override
    protected List<Gsod> search(Pair<String, String> query) {
        return service.findAllGsodByIdAndDate(query.getFirst(), query.getSecond());
    }
}
