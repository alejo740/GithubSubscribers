package com.github.globant.githubsubscribers.subscribersdetail.interactor;

import com.github.globant.githubsubscribers.commons.models.Repository;
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
        void onFinishedUser(User userItem);
        void onFailureUser(String errorMessage);

        void onFinishedRepository(List<Repository> repositoryList);
        void onFailureRepository(String errorMessage);
    }

    void getUserData(String userName, OnFinishedListener listener);

    void getUserRepositoryData(String userName, OnFinishedListener listener);
}
