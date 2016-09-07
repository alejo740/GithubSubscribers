package com.github.globant.githubsubscribers.commons.utils;

import android.util.Log;

/**
 * @author edwin.cobos
 * @since 06/09/2016
 */
public class Debug {
    public static void i(String msg) {
        if (Constants.DEBUG_LOGS) {
            Log.i(Constants.DEBUG_PREFIX, msg);
        }
    }

    public static void e(String msg) {
        if (Constants.DEBUG_LOGS) {
            Log.e(Constants.DEBUG_PREFIX, msg);
        }
    }

    public static void d(String msg) {
        if (Constants.DEBUG_LOGS) {
            Log.d(Constants.DEBUG_PREFIX, msg);
        }
    }
}
