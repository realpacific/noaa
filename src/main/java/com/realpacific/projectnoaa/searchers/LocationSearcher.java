package com.realpacific.projectnoaa.searchers;

import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Record;
import com.realpacific.projectnoaa.exceptions.InvalidInputException;
import com.realpacific.projectnoaa.readers.MultiInputConsoleReader;
import com.realpacific.projectnoaa.readers.Reader;
import com.realpacific.projectnoaa.utils.NumberUtils;

import java.util.List;
import java.util.stream.Collectors;

class LocationSearcher extends Searcher<Pair<Double, Double>> {
    LocationSearcher(List<Record> records) {
        super(records);
    }

    @Override
    protected Pair<Double, Double> convert(Object query) {
        List<String> formatterQuery = (List<String>) query;
        if (formatterQuery == null || formatterQuery.size() != getNumberOfInputsRequired()) {
            throw new InvalidInputException("Invalid input. Query requires input of " + getNumberOfInputsRequired());
        }
        return new Pair<>(new Double(formatterQuery.get(0)), new Double(formatterQuery.get(1)));
    }

    @Override
    protected boolean isValid(Pair<Double, Double> query) {
        return query.getFirst() != null && query.getSecond() != null;
    }

    @Override
    protected List<Record> search(Pair<Double, Double> query) {
        return getRecords().stream()
                .filter(
                        record -> {
                            if (record.getLatitude() == null || record.getLongitude() == null) return false;
                            else if (!NumberUtils.isNumber(record.getLatitude()) || !NumberUtils.isNumber(record.getLongitude()))
                                return false;
                            else return isWithinRequiredRadius(query, record, 50.0);
                        })
                .collect(Collectors.toList());
    }

    private boolean isWithinRequiredRadius(Pair<Double, Double> query, Record record, Double radius) {
        return distanceBetweenInKM(query,
                new Pair<>(Double.valueOf(record.getLatitude()), Double.valueOf(record.getLongitude()))) < radius;
    }


    private Double distanceBetweenInKM(Pair<Double, Double> sourceLatLngInDegrees, Pair<Double, Double> destinationLatLngInDegrees) {
        // dist = arccos(sin(lat1) · sin(lat2) + cos(lat1) · cos(lat2) · cos(lon1 - lon2)) · R
        Pair<Double, Double> sourceLatLngInRadians = convertLatLngFromDegreeToRadians(sourceLatLngInDegrees);
        Pair<Double, Double> destinationLatLngInRadians = convertLatLngFromDegreeToRadians(destinationLatLngInDegrees);

        return Math.acos(
                Math.sin(sourceLatLngInRadians.getFirst()) * Math.sin(destinationLatLngInRadians.getFirst())
                        + Math.cos(sourceLatLngInRadians.getFirst()) * Math.cos(destinationLatLngInRadians.getFirst())
                        * Math.cos(sourceLatLngInRadians.getSecond() - destinationLatLngInRadians.getSecond())
        ) * AppConstants.RADIUS_OF_EARTH;
    }

    private Pair<Double, Double> convertLatLngFromDegreeToRadians(Pair<Double, Double> latLngInDegrees) {
        return new Pair<>(Math.toRadians(latLngInDegrees.getFirst()), Math.toRadians(latLngInDegrees.getSecond()));
    }

    @Override
    public Reader getInputReader() {
        return new MultiInputConsoleReader(getNamesOfInput());
    }

    @Override
    public int getNumberOfInputsRequired() {
        return 2;
    }

    @Override
    public String[] getNamesOfInput() {
        return new String[]{"Latitude", "Longitude"};
    }
}
