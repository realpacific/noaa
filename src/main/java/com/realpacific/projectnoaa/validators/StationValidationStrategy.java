package com.realpacific.projectnoaa.validators;

import com.realpacific.projectnoaa.entities.Station;

import java.util.regex.Pattern;

public class StationValidationStrategy implements Validator<Station> {

    @Override
    public boolean isValid(Station station) {
        return !Pattern.compile("^(9{6})$").matcher(station.getUsafId()).matches();
    }
}
