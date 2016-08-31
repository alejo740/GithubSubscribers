package com.github.globant.githubsubscribers.subscribersdetail.interactor;

import com.github.globant.githubsubscribers.commons.models.User;

import java.util.List;

/**
 * Interface UserInteractor that represents interactor class(User Model) to deliver the data to presenter.
 *
 * @author juan.herrera
 * @since 30/08/2016
 */
public interface UserInteractor {
    interface OnFinishedListener {
        void onFinished(List<User> listItems);
        void onFailed(String errorMessage);
    }

    void getUserDataList(OnFinishedListener listener, String userName);
}
