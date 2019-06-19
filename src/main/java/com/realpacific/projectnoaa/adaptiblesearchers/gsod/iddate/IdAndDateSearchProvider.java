package com.realpacific.projectnoaa.adaptiblesearchers.gsod.iddate;

import com.realpacific.projectnoaa.adaptiblesearchers.HasDatabase;
import com.realpacific.projectnoaa.adaptiblesearchers.HasInMemory;
import com.realpacific.projectnoaa.adaptiblesearchers.SearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.Searcher;
import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.services.DatabaseService;
import com.realpacific.projectnoaa.services.GsodService;

import java.util.List;

public class IdAndDateSearchProvider implements HasDatabase, HasInMemory<Gsod> {
    @Override
    public Searcher get(DatabaseService service) {
        if (!(service instanceof GsodService))
            throw new IllegalArgumentException("Invalid argument. Must be of type GsodService.");
        return new DatabaseIdAndDateSearcher((GsodService) service);
    }

    @Override
    public Searcher get(List<Gsod> data) {
        return new InMemoryIdAndDateSearcher(data);
    }
}
