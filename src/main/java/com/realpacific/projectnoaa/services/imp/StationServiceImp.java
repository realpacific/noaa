package com.realpacific.projectnoaa.services.imp;

import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.repositories.StationRepository;
import com.realpacific.projectnoaa.services.StationService;
import com.realpacific.projectnoaa.validators.StationValidationStrategy;
import com.realpacific.projectnoaa.validators.Validator;

import java.util.List;

public class StationServiceImp implements StationService {
    private StationRepository repository = new StationRepository();
    private Validator<Station> validator = new StationValidationStrategy();

    @Override
    public void bulkSave(List<Station> stations) {
        for (Station station : stations) {
            if (validator.isValid(station)) repository.saveOne(station);
        }
    }

    @Override
    public Station findStationByUsafId(String usafId) {
        return repository.findStationById(usafId);
    }

    @Override
    public List<Station> findAllStations() {
        return repository.findAllStations();
    }

    @Override
    public List<Station> findAllStationsByCountry(String country) {
        return repository.findAllStationsByCountry(country);
    }

    @Override
    public List<Station> findAllStationsByName(String name) {
        return repository.findAllStationsByName(name);
    }

    @Override
    public List<Station> findAllStationsByIdRange(String begin, String end) {
        return repository.findAllStationsByIdRange(begin, end);
    }

    @Override
    public List<Station> findAllStationsByLocationRange(double latitude, double longitude, double radius) {
        return repository.findAllStationsWithinLocationRange(latitude, longitude, radius);
    }
}
