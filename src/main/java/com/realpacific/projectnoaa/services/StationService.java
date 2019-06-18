package com.realpacific.projectnoaa.services;

import com.realpacific.projectnoaa.entities.Station;

import java.util.List;

public interface StationService extends DatabaseService {
    void bulkSave(List<Station> stations);

    List<Station> findAllStations();

    List<Station> findAllStationsByCountry(String country);

    List<Station> findAllStationsByName(String name);

    List<Station> findAllStationsByIdRange(String begin, String end);

    List<Station> findAllStationsByLocationRange(double latitude, double longitude, double radius);
}
