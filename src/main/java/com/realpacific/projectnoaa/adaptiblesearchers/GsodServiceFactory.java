package com.realpacific.projectnoaa.adaptiblesearchers;

import com.realpacific.projectnoaa.adaptiblesearchers.gsod.iddate.IdAndDateSearchProvider;
import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.services.GsodService;

public class GsodServiceFactory {

    public static Searcher getSearcher(String code, GsodService service) {
        SearchProvider<Gsod> searchProvider = resolveSearchProvider(code);
        return searchProvider.get(service);
    }

    private static SearchProvider<Gsod> resolveSearchProvider(String code) {
        SearchProvider<Gsod> searchProvider;
        switch (code) {
            case "2":
                searchProvider = new IdAndDateSearchProvider();
                break;
            default:
                throw new InvalidInputException("Invalid option value. Should be between 1~5.");
        }
        return searchProvider;
    }
}
