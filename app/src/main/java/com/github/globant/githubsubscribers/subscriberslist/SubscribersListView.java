package com.github.globant.githubsubscribers.subscriberslist;

import com.github.globant.githubsubscribers.commons.models.Subscriber;

import java.util.List;

/**
 * Interface SubscribersListView that represents the View class(Activity)
 *
 * @author edwin.cobos
 * @since 19/08/2016
 */
public interface SubscribersListView {

    void navigateToUserDetail(List<Subscriber> subscriberList);

}
