package com.adanlm.series.utils;

public class CommonUtils {

    public static String cleanHTMLText(String stringToClean) {
        return stringToClean.replaceAll("\\<.*?\\>", "");
    }

    public static String cleanBracketsText(String stringToClean) {
        return stringToClean.replaceAll("[\\[\\]']+", "");
    }
}
