package com.realpacific.projectnoaa.adaptiblesearchers.gsod.date;

import com.realpacific.projectnoaa.adaptiblesearchers.SearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.adaptiblesearchers.gsod.countrydate.DatabaseCountryAndDateSearcher;
import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.services.DatabaseService;
import com.realpacific.projectnoaa.services.GsodService;

import java.util.List;

public class AvailableDateSearchProvider implements SearchProvider<Gsod> {
    @Override
    public Searcher get(DatabaseService service) {
        if (!(service instanceof GsodService))
            throw new IllegalArgumentException("Invalid argument. Must be of type GsodService.");
        return new DatabaseAvailableDateSearcher((GsodService) service);
    }

    @Override
    public Searcher get(List<Gsod> data) {
        return null;
    }
}
