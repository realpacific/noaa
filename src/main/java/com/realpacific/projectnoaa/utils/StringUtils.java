package com.realpacific.projectnoaa.utils;

public class StringUtils {
    public static boolean isInBetween(String valueToCompare, String minValue, String maxValue) {
        return valueToCompare.compareTo(minValue) >= 0 && valueToCompare.compareTo(maxValue) <= 0;
    }
}
