package com.github.globant.githubsubscribers.commons.utils;

import android.util.Log;

/**
 * Utils class that works like a collection of static methods that operate on or return a value.
 *
 * @author edwin.cobos
 * @since 19/08/2016
 */
public class Utils {

    public static void debugLog(String msg) {
        if (Constants.DEBUG_LOGS) {
            Log.i(Constants.DEBUG_PREFIX, msg);
        }
    }

}
