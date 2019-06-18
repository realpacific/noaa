package com.realpacific.projectnoaa.adaptiblesearchers.gsod.date;

import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.services.GsodService;

import java.util.List;

public class DatabaseAvailableDateSearcher extends AvailableDateSearcher {
    private GsodService service;

    public DatabaseAvailableDateSearcher(GsodService service) {
        this.service = service;
    }

    @Override
    protected List<String> search(Pair<String, String> query) {

        return null;
    }
}
