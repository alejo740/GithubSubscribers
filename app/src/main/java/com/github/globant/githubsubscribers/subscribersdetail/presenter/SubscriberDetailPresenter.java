package com.github.globant.githubsubscribers.subscribersdetail.presenter;

import com.github.globant.githubsubscribers.commons.models.Repository;
import com.github.globant.githubsubscribers.commons.models.User;

import java.util.List;

/**
 * Interface SubscriberDetailPresenter that represents the presenter class to communicate the Activity
 * class(view) and Interactor class(User Model).
 *
 * @author juan.herrera
 * @author edwin.cobos
 * @since 30/08/2016
 */
public interface SubscriberDetailPresenter {

    void onDestroy();

    void getUser(String userName);

    void getRepositoryList(String userName);

    User getUserData();

    List<Repository> getRepositoryListData();
}
