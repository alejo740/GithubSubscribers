package com.github.globant.githubsubscribers.subscriberslist.presenter;

/**
 * Interface SubscribersListPresenter that represents the presenter class to communicate the Activity class(view) and Interactor class(Model).
 *
 * @author edwin.cobos
 * @since 18/08/2016
 */
public interface SubscribersListPresenter {

    void onDestroy();

    void getSubscribersList();
}
