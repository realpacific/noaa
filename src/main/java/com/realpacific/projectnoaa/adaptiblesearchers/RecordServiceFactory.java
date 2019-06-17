package com.realpacific.projectnoaa.adaptiblesearchers;

import com.realpacific.projectnoaa.adaptiblesearchers.record.country.CountrySearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.record.id.IdSearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.record.location.LocationSearchProvider;
import com.realpacific.projectnoaa.adaptiblesearchers.record.name.NameSearchProvider;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.services.RecordService;

import java.util.List;

public class RecordServiceFactory {
    public static Searcher getSearcher(String code, RecordService service) {
        SearchProvider searchProvider = resolveSearchProvider(code);
        if(searchProvider == null) return null;
        return searchProvider.get(service);
    }

    public static Searcher getSearcher(String code, List<Record> records) {
        SearchProvider searchProvider = resolveSearchProvider(code);
        return searchProvider.get(records);
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
