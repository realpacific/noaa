package com.realpacific.projectnoaa.adaptiblesearchers;

import com.realpacific.projectnoaa.adaptiblesearchers.station.country.CountrySearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.station.id.IdSearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.station.location.LocationSearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.station.name.NameSearchProvider;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.services.StationService;

import java.util.List;

public class StationSearchProviderFactory {
    public static Searcher getSearcher(String code, StationService service) {
        SearchProvider searchProvider = resolveSearchProvider(code);
        if(searchProvider == null) return null;
        return searchProvider.get(service);
    }

    public static Searcher getSearcher(String code, List<Station> stations) {
        SearchProvider searchProvider = resolveSearchProvider(code);
        return searchProvider.get(stations);
    }

    private static SearchProvider resolveSearchProvider(String code) {
        SearchProvider searchProvider;
        switch (code) {
            case "1":
                searchProvider = new NameSearchProvider();
                break;
            case "2":
                searchProvider = new CountrySearchProvider();
                break;

            case "3":
                searchProvider = new IdSearchProvider();
                break;
            case "4":
                searchProvider = new LocationSearchProvider();
                break;
            case "5":
                searchProvider = null;
                break;
            default:
                throw new InvalidInputException("Invalid option value. Should be between 1~5.");
        }
        return searchProvider;
    }
}
