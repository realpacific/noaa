package com.realpacific.projectnoaa.adaptiblesearchers;

import com.realpacific.projectnoaa.adaptiblesearchers.gsod.countrydate.CountryAndDateSearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.gsod.date.AvailableDateSearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.gsod.iddate.IdAndDateSearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.gsod.namedate.NameAndDateSearchProvider;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.services.GsodService;

public class GsodSearchProviderFactory {

    public static Searcher getSearcher(String code, GsodService service) {
        HasDatabase searchProvider = resolveSearchProvider(code);
        if (searchProvider == null) return null;
        return searchProvider.get(service);
    }

    private static HasDatabase resolveSearchProvider(String code) {
        HasDatabase searchProvider;
        switch (code) {
            case "1":
                searchProvider = new AvailableDateSearchProvider();
                break;
            case "2":
                searchProvider = new IdAndDateSearchProvider();
                break;
            case "3":
                searchProvider = new CountryAndDateSearchProvider();
                break;
            case "4":
                searchProvider = new NameAndDateSearchProvider();
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
