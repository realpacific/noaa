package com.realpacific.projectnoaa.adaptiblesearchers.gsod.countrydate;

import com.realpacific.projectnoaa.adaptiblesearchers.SearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.adaptiblesearchers.gsod.iddate.DatabaseIdAndDateSearcher;
import com.realpacific.projectnoaa.adaptiblesearchers.gsod.iddate.InMemoryIdAndDateSearcher;
import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.services.DatabaseService;
import com.realpacific.projectnoaa.services.GsodService;

import java.util.List;

public class CountryAndDateSearchProvider implements SearchProvider<Gsod> {
    @Override
    public Searcher get(DatabaseService service) {
        if (!(service instanceof GsodService))
            throw new IllegalArgumentException("Invalid argument. Must be of type GsodService.");
        return new DatabaseCountryAndDateSearcher((GsodService) service);
    }

    @Override
    public Searcher get(List<Gsod> data) {
        return null;
    }
}
