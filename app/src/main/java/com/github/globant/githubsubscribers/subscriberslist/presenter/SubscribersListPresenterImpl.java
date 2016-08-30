package com.github.globant.githubsubscribers.subscriberslist.presenter;

import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.github.globant.githubsubscribers.commons.utils.Utils;
import com.github.globant.githubsubscribers.subscriberslist.view.SubscribersListView;
import com.github.globant.githubsubscribers.subscriberslist.interactor.SubscribersListInteractor;
import com.github.globant.githubsubscribers.subscriberslist.interactor.SubscribersListInteractorImpl;

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
        view.showSubscribersList(listItems);
        //Utils.debugLog(Arrays.deepToString(listItems.toArray()));
    }

    @Override
    public void onFailed(String errorMessage) {
        Utils.debugLog(errorMessage);
    }
}
