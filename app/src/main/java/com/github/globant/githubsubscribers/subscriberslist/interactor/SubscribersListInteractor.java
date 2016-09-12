package com.github.globant.githubsubscribers.subscriberslist.interactor;

import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.github.globant.githubsubscribers.commons.utils.ErrorMessagesHelper;

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
        void onResponse(List<Subscriber> listItems);

        void onFailure(String errorMessage, ErrorMessagesHelper.TypeError type);
    }

    void getSubscribersDataList(OnFinishedListener listener);

    void onCancelRequest();
}
