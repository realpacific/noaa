package com.realpacific.projectnoaa.services;

import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.services.imp.StationServiceImp;
import com.realpacific.projectnoaa.utils.StringUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StationServiceTest {

    private StationService service = new StationServiceImp();

    @Test
    public void testFor_findAllStationsByCountry() {
        List<Station> stationsList = service.findAllStationsByCountry("AF");
        for (Station station : stationsList) {
            assertEquals("AF", station.getCountry());
        }
    }


    @Test
    public void testFor_findAllStationsByIdRange() {
        String maxValue = "700000";
        String minValue = "690000";
        List<Station> stationsList = service.findAllStationsByIdRange(minValue, maxValue);
        for (Station station : stationsList) {
            assertTrue(StringUtils.isInBetween(station.getUsafId(), minValue, maxValue));
        }
    }

    @Test
    public void testFor_findAllStationsByName() {
        String nameQuery = "CWOS";
        List<Station> stationsList = service.findAllStationsByName(nameQuery);
        for (Station station : stationsList) {
            assertTrue(station.getStationName().contains(nameQuery));
        }
    }
}