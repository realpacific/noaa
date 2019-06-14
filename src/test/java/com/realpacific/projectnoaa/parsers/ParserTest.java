package com.realpacific.projectnoaa.parsers;

import com.realpacific.projectnoaa.entities.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.realpacific.projectnoaa.regex.BracketFormatter;

import java.util.Arrays;
import java.util.Map;

public class ParserTest {

    private Parser<Map<String, Pair<Integer, Integer>>> parser;
    private final String HEADER = "USAF   WBAN  STATION NAME                  CTRY ST CALL  LAT     LON      ELEV(M) BEGIN    END";

    @Before
    public void setUp() {
        parser = new FileHeaderParser(Arrays.asList("USAF", "WBAN", "STATION NAME", "CTRY", "ST",
                "CALL", "LAT", "LON", "ELEV(M)", "BEGIN", "END"), new BracketFormatter());
    }

    @Test
    public void test() {
        Map<String, Pair<Integer, Integer>> spacingForHeadlineMap = parser.parse(HEADER);
        Assert.assertEquals(new Pair<>(0, 6), spacingForHeadlineMap.get("USAF"));
        Assert.assertEquals(new Pair<>(7, 12), spacingForHeadlineMap.get("WBAN"));
        Assert.assertEquals(new Pair<>(13, 42), spacingForHeadlineMap.get("STATION NAME"));
        Assert.assertEquals(new Pair<>(43, 47), spacingForHeadlineMap.get("CTRY"));
        Assert.assertEquals(new Pair<>(48, 50), spacingForHeadlineMap.get("ST"));
        Assert.assertEquals(new Pair<>(51, 56), spacingForHeadlineMap.get("CALL"));
        Assert.assertEquals(new Pair<>(57, 64), spacingForHeadlineMap.get("LAT"));
        Assert.assertEquals(new Pair<>(65, 73), spacingForHeadlineMap.get("LON"));
        Assert.assertEquals(new Pair<>(74, 81), spacingForHeadlineMap.get("ELEV(M)"));
        Assert.assertEquals(new Pair<>(82, 90), spacingForHeadlineMap.get("BEGIN"));
        Assert.assertEquals(new Pair<>(91, Integer.MAX_VALUE), spacingForHeadlineMap.get("END"));
    }
}