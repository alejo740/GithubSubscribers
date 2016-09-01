package com.github.globant.githubsubscribers.subscribersdetail.view;

import com.github.globant.githubsubscribers.commons.models.Repository;
import com.github.globant.githubsubscribers.commons.models.User;

import java.util.List;

/**
 * @author edwin.cobos
 * @since 31/08/2016
 */
public interface SubscriberDetailView {

    void showSubscriberDetails(User userInfo);

    void showSubscriberRepositories(List<Repository> repositories);

}
