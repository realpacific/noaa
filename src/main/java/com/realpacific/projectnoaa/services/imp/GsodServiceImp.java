package com.realpacific.projectnoaa.services.imp;

import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.repositories.GsodRepository;
import com.realpacific.projectnoaa.services.GsodService;
import com.realpacific.projectnoaa.services.StationService;

import java.util.List;

public class GsodServiceImp implements GsodService {
    private GsodRepository repository = new GsodRepository();
    private StationService stationService = new StationServiceImp();


    @Override
    public void bulkSave(List<Gsod> gsods) {
        for (Gsod gsod : gsods) {
            Station station = stationService.findStationByUsafId(gsod.getStationNumber());
            gsod.setStation(station);
            repository.saveOne(gsod);
        }
    }

    @Override
    public List<Gsod> findAllGsodByIdAndDate(String id, String date) {
        return repository.findAllGsodsByIdAndDate(id, date);
    }

    @Override
    public List<Gsod> findAllGsodByCountryAndDate(String country, String date) {
        return repository.findAllGsodsByCountryAndDate(country, date);
    }

    @Override
    public List<Gsod> findAllGsodByNameAndDate(String name, String date) {
        return repository.findAllGsodsByStationNameAndDate(name, date);
    }

    @Override
    public List<String> findAllAvailableDates() {
        return repository.findAllAvailableDates();
    }
}
