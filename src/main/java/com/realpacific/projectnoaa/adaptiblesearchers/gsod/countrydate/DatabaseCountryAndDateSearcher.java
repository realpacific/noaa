package com.realpacific.projectnoaa.adaptiblesearchers.gsod.countrydate;

import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.services.GsodService;

import java.util.List;

public class DatabaseCountryAndDateSearcher extends CountryAndDateSearcher {
    private GsodService service;

    public DatabaseCountryAndDateSearcher(GsodService service) {
        this.service = service;
    }

    @Override
    protected List<Gsod> search(Pair<String, String> query) {
        return service.findAllGsodByCountryAndDate(query.getFirst(), query.getSecond());
    }
}
