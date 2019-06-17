package com.realpacific.projectnoaa.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
    public final static Double RADIUS_OF_EARTH = 6371.0;

    public final static List<String> FILE_HEADERS_STATIONS = Arrays.asList("USAF", "WBAN", "STATION NAME", "CTRY", "ST",
            "CALL", "LAT", "LON", "ELEV(M)", "BEGIN", "END");

    public final static List<String> FILE_HEADERS_GSOD = Arrays.asList("STN---", "WBAN", "YEARMODA", "TEMP", "DEWP",
            "SLP", "STP", "VISIB", "WDSP", "MXSPD", "GUST", "MAX", "MIN", "PRCP", "SNDP", "FRSHTT");

}
