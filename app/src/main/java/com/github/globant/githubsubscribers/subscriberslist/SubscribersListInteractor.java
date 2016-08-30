package com.github.globant.githubsubscribers.subscriberslist;

import com.github.globant.githubsubscribers.commons.models.Subscriber;

import java.util.List;

/**
 * Interface SubscribersListInteractor that represents interactor class(Model) to deliver the data to presenter.
 *
 * @author edwin.cobos
 * @author juan.herrera
 * @since 19/08/2016
 */
public interface SubscribersListInteractor {
    interface OnFinishedListener {
        void onFinished(List<Subscriber> listItems);
        void onFailed(String errorMessage);
    }

    void getSubscribersDataList(OnFinishedListener listener);

}
