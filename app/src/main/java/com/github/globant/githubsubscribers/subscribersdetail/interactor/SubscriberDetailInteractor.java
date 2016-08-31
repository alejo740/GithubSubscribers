package com.github.globant.githubsubscribers.subscribersdetail.interactor;

import com.github.globant.githubsubscribers.commons.models.User;

import java.util.List;

/**
 * Interface SubscriberDetailInteractor that represents interactor class(User Model) to deliver the data to presenter.
 *
 * @author juan.herrera
 * @since 30/08/2016
 */
public interface SubscriberDetailInteractor {
    interface OnFinishedListener {
        void onFinished(User userItem);
        void onFailure(String errorMessage);
    }

    void getUserData(OnFinishedListener listener, String userName);
}
