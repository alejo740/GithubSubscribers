package com.github.globant.githubsubscribers.subscribersdetail.view;

import com.github.globant.githubsubscribers.commons.models.Repository;
import com.github.globant.githubsubscribers.commons.models.User;

import java.util.List;

/**
 * Interface SubscriberDetailView that represents the View class(Activity)
 *
 * @author edwin.cobos
 * @author juan.herrera
 * @since 31/08/2016
 */
public interface SubscriberDetailView {

    void showSubscriberDetails(User userInfo);

    void showSubscriberUserRepositories(List<Repository> repositoryList);

    void showUserError(int messageId);

    void toggleProgressIndicator(boolean active);
}
