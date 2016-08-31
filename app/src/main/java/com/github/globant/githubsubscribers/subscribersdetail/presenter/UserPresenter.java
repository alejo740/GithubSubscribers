package com.github.globant.githubsubscribers.subscribersdetail.presenter;

/**
 * Interface UserPresenter that represents the presenter class to communicate the Activity
 * class(view) and Interactor class(User Model).
 *
 * @author juan.herrera
 * @since 30/08/2016
 */
public interface UserPresenter {

    void onResume();

    void onDestroy();

    void getSubscribersList(String userName);
}
