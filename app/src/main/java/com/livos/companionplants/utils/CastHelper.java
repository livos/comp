package com.livos.companionplants.utils;

/**
 * Created by laurent on 2/3/18.
 */

public class CastHelper {
    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }
}
