package com.github.globant.githubsubscribers.subscribersdetail.presenter;

/**
 * Interface SubscriberDetailPresenter that represents the presenter class to communicate the Activity
 * class(view) and Interactor class(User Model).
 *
 * @author juan.herrera
 * @since 30/08/2016
 */
public interface SubscriberDetailPresenter {

    void onDestroy();

    void getUser(String userName);

    void getRepositoryList(String userName);
}
