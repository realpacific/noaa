package com.realpacific.projectnoaa.parsers;

import com.realpacific.projectnoaa.constants.AppConstants;
import com.realpacific.projectnoaa.entities.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.realpacific.projectnoaa.formatters.BracketFormatter;

import java.util.Map;

public class ParserTest {

    private Parser<Map<String, Pair<Integer, Integer>>> parserStation;
    private Parser<Map<String, Pair<Integer, Integer>>> parserGsod;
    private final String HEADER = "USAF   WBAN  STATION NAME                  CTRY ST CALL  LAT     LON      ELEV(M) BEGIN    END";
    private final String HEADER_GSOD = "STN--- WBAN   YEARMODA    TEMP       DEWP      SLP        STP       VISIB      WDSP     MXSPD   GUST    MAX     MIN   PRCP   SNDP   FRSHTT ";

    @Before
    public void setUp() {
        parserStation = new FileHeaderToColumnWidthParser(AppConstants.FILE_HEADERS_STATIONS, new BracketFormatter());

        parserGsod = new FileHeaderToColumnWidthParser(AppConstants.FILE_HEADERS_GSOD, new BracketFormatter());
    }

    @Test
    public void test() {
        Map<String, Pair<Integer, Integer>> spacingForHeadlineMap = parserStation.parse(HEADER);
        Assert.assertEquals(new Pair<>(0, 6), spacingForHeadlineMap.get("USAF"));
        Assert.assertEquals(new Pair<>(6, 12), spacingForHeadlineMap.get("WBAN"));
        Assert.assertEquals(new Pair<>(12, 42), spacingForHeadlineMap.get("STATION NAME"));
        Assert.assertEquals(new Pair<>(42, 47), spacingForHeadlineMap.get("CTRY"));
        Assert.assertEquals(new Pair<>(47, 50), spacingForHeadlineMap.get("ST"));
        Assert.assertEquals(new Pair<>(50, 56), spacingForHeadlineMap.get("CALL"));
        Assert.assertEquals(new Pair<>(56, 64), spacingForHeadlineMap.get("LAT"));
        Assert.assertEquals(new Pair<>(64, 73), spacingForHeadlineMap.get("LON"));
        Assert.assertEquals(new Pair<>(73, 81), spacingForHeadlineMap.get("ELEV(M)"));
        Assert.assertEquals(new Pair<>(81, 90), spacingForHeadlineMap.get("BEGIN"));
        Assert.assertEquals(new Pair<>(90, Integer.MAX_VALUE), spacingForHeadlineMap.get("END"));
    }


    @Test
    public void testForGsodFile() {
        Map<String, Pair<Integer, Integer>> spacingForHeadlineMap = parserGsod.parse(HEADER_GSOD);
        Assert.assertEquals(new Pair<>(0, 6), spacingForHeadlineMap.get("STN---"));
        Assert.assertEquals(new Pair<>(6, 13), spacingForHeadlineMap.get("WBAN"));
        Assert.assertEquals(new Pair<>(13, 25), spacingForHeadlineMap.get("YEARMODA"));
        Assert.assertEquals(new Pair<>(25, 36), spacingForHeadlineMap.get("TEMP"));
        Assert.assertEquals(new Pair<>(36, 46), spacingForHeadlineMap.get("DEWP"));
        Assert.assertEquals(new Pair<>(46, 57), spacingForHeadlineMap.get("SLP"));
        Assert.assertEquals(new Pair<>(57, 67), spacingForHeadlineMap.get("STP"));
        Assert.assertEquals(new Pair<>(67, 78), spacingForHeadlineMap.get("VISIB"));
        Assert.assertEquals(new Pair<>(78, 87), spacingForHeadlineMap.get("WDSP"));
        Assert.assertEquals(new Pair<>(87, 95), spacingForHeadlineMap.get("MXSPD"));
        Assert.assertEquals(new Pair<>(95, 103), spacingForHeadlineMap.get("GUST"));
        Assert.assertEquals(new Pair<>(103, 111), spacingForHeadlineMap.get("MAX"));
        Assert.assertEquals(new Pair<>(111, 117), spacingForHeadlineMap.get("MIN"));
        Assert.assertEquals(new Pair<>(117, 124), spacingForHeadlineMap.get("PRCP"));
        Assert.assertEquals(new Pair<>(124, 131), spacingForHeadlineMap.get("SNDP"));
        Assert.assertEquals(new Pair<>(131, Integer.MAX_VALUE), spacingForHeadlineMap.get("FRSHTT"));
    }
}