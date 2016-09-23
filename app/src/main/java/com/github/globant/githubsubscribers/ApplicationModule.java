package com.github.globant.githubsubscribers;

import android.app.Application;

import dagger.Module;

/**
 * @author edwin.cobos
 * @since 23/09/2016
 */

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }
}
