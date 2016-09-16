package com.github.globant.githubsubscribers.commons.utils;

/**
 * Constants class used to manage all constants of the App code
 *
 * @author edwin.cobos
 * @since 23/08/2016
 */
public class Constants {

    public static final String GITHUB_BASE_URL = "https://api.github.com/";

    public static final String DEBUG_PREFIX = "GITHUBAPI";
    public static final boolean DEBUG_LOGS = true;

    public static final long SPLASH_DELAY = 3000;
    public static final String EXCEPTION_ERROR = "Exception";

    public final static class SplashLogoAnimation {
        public final static long START_DELAY = 200;
        public final static long DURATION = 1300;
        public final static float INIT_SCALE = 0.0f;
        public final static float FINAL_SCALE = 1.0f;
    }

}
