package com.realpacific.projectnoaa.searchers;

import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;

import java.util.List;

public class SearcherFactory {

    public static Searcher getSearcher(String code, List<Record> records) {
        switch (code) {
            case "1":
                return new NameSearcher(records);
            case "2":
                return new CountrySearcher(records);
            case "3":
                return new IdSearcher(records);
            case "4":
                return new LocationSearcher(records);
            case "5":
                return null;
            default:
                throw new InvalidInputException("Invalid option value. Should be between 1~5.");
        }
    }


}
