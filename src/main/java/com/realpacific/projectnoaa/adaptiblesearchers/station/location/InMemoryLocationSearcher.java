package com.realpacific.projectnoaa.adaptiblesearchers.station.location;

import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.utils.NumberUtils;

import java.util.List;
import java.util.stream.Collectors;

class InMemoryLocationSearcher extends LocationSearcher {
    private List<Station> stations;

    public InMemoryLocationSearcher(List<Station> stations) {
        this.stations = stations;
    }


    @Override
    protected List<Station> search(Pair<Double, Double> query) {
        return stations.stream()
                .filter(
                        record -> {
                            if (record.getLatitude() == null || record.getLongitude() == null) return false;
                            else if (!NumberUtils.isNumber(record.getLatitude()) || !NumberUtils.isNumber(record.getLongitude()))
                                return false;
                            else return isWithinRequiredRadius(query, record, 50.0);
                        })
                .collect(Collectors.toList());
    }

    private boolean isWithinRequiredRadius(Pair<Double, Double> query, Station station, Double radius) {
        return distanceBetweenInKM(query,
                new Pair<>(Double.valueOf(station.getLatitude()), Double.valueOf(station.getLongitude()))) < radius;
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
}
