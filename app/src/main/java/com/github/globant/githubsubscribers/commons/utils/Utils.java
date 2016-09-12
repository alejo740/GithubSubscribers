package com.github.globant.githubsubscribers.commons.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
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

    public static void hideBar(AppCompatActivity activity) {
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().hide();
        }
    }

    public static SpannableString setUnderlineText(String someText) {
        SpannableString spanString = new SpannableString(someText);
        spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);
        return spanString;
    }

    public static void openLinkInBrowser(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

}
