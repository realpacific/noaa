package com.realpacific.projectnoaa.adaptiblesearchers.gsod.countrydate;

import com.realpacific.projectnoaa.adaptiblesearchers.HasDatabase;
import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.services.DatabaseService;
import com.realpacific.projectnoaa.services.GsodService;

public class CountryAndDateSearchProvider implements HasDatabase {
    @Override
    public Searcher get(DatabaseService service) {
        if (!(service instanceof GsodService))
            throw new IllegalArgumentException("Invalid argument. Must be of type GsodService.");
        return new DatabaseCountryAndDateSearcher((GsodService) service);
    }
}
