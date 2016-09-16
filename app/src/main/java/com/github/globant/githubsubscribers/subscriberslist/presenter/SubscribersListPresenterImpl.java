package com.github.globant.githubsubscribers.subscriberslist.presenter;

import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.github.globant.githubsubscribers.commons.utils.Debug;
import com.github.globant.githubsubscribers.commons.utils.ErrorMessagesHelper;
import com.github.globant.githubsubscribers.subscriberslist.interactor.SubscribersListInteractor;
import com.github.globant.githubsubscribers.subscriberslist.interactor.SubscribersListInteractorImpl;
import com.github.globant.githubsubscribers.subscriberslist.view.SubscribersListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter class that connects the Activity class(view) and Interactor class(Model).
 * This class implements SubscribersListPresenter interface.
 *
 * @author edwin.cobos
 * @author juan.herrera
 * @since 18/08/2016
 */
public class SubscribersListPresenterImpl implements SubscribersListPresenter, SubscribersListInteractor.OnFinishedListener {

    private SubscribersListView view;
    private SubscribersListInteractor interactor;
    private List<Subscriber> subscribersListData;
    private final String TAG;

    public SubscribersListPresenterImpl(SubscribersListView view) {
        this.view = view;
        this.interactor = new SubscribersListInteractorImpl();
        this.subscribersListData = new ArrayList<>();
        this.TAG = this.getClass().getSimpleName();
    }

    public SubscribersListPresenterImpl(SubscribersListView view, List<Subscriber> subscribersListData) {
        this.view = view;
        this.interactor = new SubscribersListInteractorImpl();
        this.subscribersListData = subscribersListData;
        this.TAG = this.getClass().getSimpleName();
    }

    public SubscribersListPresenterImpl(SubscribersListView view, SubscribersListInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
        this.subscribersListData = new ArrayList<>();
        this.TAG = this.getClass().getSimpleName();
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor.onCancelRequest();
    }

    @Override
    public void getSubscribersList() {
        view.toggleProgress(true);
        interactor.getSubscribersDataList(this);
    }

    @Override
    public void onResponse(List<Subscriber> listItems) {
        subscribersListData.clear();
        if (view != null) {
            view.toggleProgress(false);
            subscribersListData.addAll(listItems);
            view.showSubscribersList(listItems);
        }
    }

    @Override
    public void onFailure(String errorMessage, ErrorMessagesHelper.TypeError type) {
        int messageId = ErrorMessagesHelper.getMessage(type);
        if (messageId > 0 && view != null) {
            view.toggleProgress(false);
            view.showSubscribersError(messageId);
        }
        Debug.e(TAG + ": " + errorMessage);
    }

    public List<Subscriber> getSubscribersListData() {
        return subscribersListData;
    }
}
