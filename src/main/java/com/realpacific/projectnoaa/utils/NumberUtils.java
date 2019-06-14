package com.realpacific.projectnoaa.utils;

public class NumberUtils {
    public static boolean isNumber(String text) {
        try {
            Double.valueOf(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
