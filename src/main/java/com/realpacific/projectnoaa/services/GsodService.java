package com.realpacific.projectnoaa.services;

import com.realpacific.projectnoaa.entities.Gsod;

import java.util.List;

public interface GsodService extends DatabaseService {
    void bulkSave(List<Gsod> gsods);

    List<Gsod> findAllGsodByIdAndDate(String id, String date);

    List<Gsod> findAllGsodByCountryAndDate(String country, String date);

    List<Gsod> findAllGsodByNameAndDate(String name, String date);

    List<Gsod> findAllAvailableDates();

}
