package com.github.globant.githubsubscribers.commons.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

    public static void openLinkInBrowser(Context context, String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }

}
