package com.realpacific.projectnoaa.adaptiblesearchers;

import com.realpacific.projectnoaa.services.DatabaseService;

public interface HasDatabase {
    Searcher get(DatabaseService service);
}
