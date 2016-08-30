package com.github.globant.githubsubscribers.subscriberslist;

import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.github.globant.githubsubscribers.commons.utils.Utils;

import java.util.Arrays;
import java.util.List;

/**
 * Presenter class that connects the Activity class(view) and Interactor class(Model).
 * This class implements SubscribersListPresenter interface.
 *
 * @author edwin.cobos
 * @since 18/08/2016
 */
public class SubscribersListPresenterImpl implements SubscribersListPresenter, SubscribersListInteractor.OnFinishedListener {

    private SubscribersListView view;
    private SubscribersListInteractor interactor;

    public SubscribersListPresenterImpl(SubscribersListView view) {
        this.view = view;
        this.interactor = new SubscribersListInteractorImpl();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getSubscribersList() {
        interactor.getSubscribersDataList(this);
    }

    @Override
    public void onFinished(List<Subscriber> listItems) {
        view.navigateToUserDetail(listItems);
        //Utils.debugLog(Arrays.deepToString(listItems.toArray()));
    }

    @Override
    public void onFailed(String errorMessage) {
        Utils.debugLog(errorMessage);
    }
}
