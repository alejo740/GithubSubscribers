package com.github.globant.githubsubscribers;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author edwin.cobos
 * @since 23/09/2016
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject();

}
