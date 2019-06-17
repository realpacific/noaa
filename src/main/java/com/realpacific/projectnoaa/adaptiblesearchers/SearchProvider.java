package com.realpacific.projectnoaa.adaptiblesearchers;

import com.realpacific.projectnoaa.services.DatabaseService;

import java.util.List;

public interface SearchProvider<T> {
    Searcher get(DatabaseService service);

    Searcher get(List<T> data);
}
