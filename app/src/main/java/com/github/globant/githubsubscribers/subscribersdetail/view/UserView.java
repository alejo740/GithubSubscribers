package com.github.globant.githubsubscribers.subscribersdetail.view;

import com.github.globant.githubsubscribers.commons.models.User;

import java.util.List;

/**
 * Interface UserView that represents the View class(Activity)
 *
 * @author juan.herrera
 * @since 30/08/2016
 */
public interface UserView {

    void showSubscriberUser(List<User> subscriberUserList);
}
