package com.github.globant.githubsubscribers.subscribersdetail.view;

import com.github.globant.githubsubscribers.commons.models.Repository;
import com.github.globant.githubsubscribers.commons.models.User;

import java.util.List;

/**
 * Interface SubscriberDetailView that represents the View class(Activity)
 *
 * @author juan.herrera
 * @since 30/08/2016
 */
public interface SubscriberDetailView {

    void showSubscriberUser(User subscriberUser);

    void showSubscriberUserRepository(List<Repository> repositoryList);

    void showUserError();

    void showRepositoryError();
}
