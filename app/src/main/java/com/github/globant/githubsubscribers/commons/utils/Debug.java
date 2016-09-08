package com.github.globant.githubsubscribers.commons.utils;

import android.util.Log;

/**
 * Debug class to manage all log types to print messages on debug process
 *
 * @author edwin.cobos
 * @since 06/09/2016
 */
public class Debug {

    /**
     * Send an INFO log message.
     *
     * @param msg
     */
    public static void i(String msg) {
        if (Constants.DEBUG_LOGS) {
            Log.i(Constants.DEBUG_PREFIX, msg);
        }
    }

    /**
     * Send an ERROR log message.
     *
     * @param msg
     */
    public static void e(String msg) {
        if (Constants.DEBUG_LOGS) {
            Log.e(Constants.DEBUG_PREFIX, msg);
        }
    }

    /**
     * Send a ERROR log message and log the exception.
     *
     * @param msg
     * @param tr
     */
    public static void e(String msg, Throwable tr) {
        if (Constants.DEBUG_LOGS) {
            Log.e(Constants.DEBUG_PREFIX, msg, tr);
        }
    }

    /**
     * Send a DEBUG log message.
     *
     * @param msg
     */
    public static void d(String msg) {
        if (Constants.DEBUG_LOGS) {
            Log.d(Constants.DEBUG_PREFIX, msg);
        }
    }
}
