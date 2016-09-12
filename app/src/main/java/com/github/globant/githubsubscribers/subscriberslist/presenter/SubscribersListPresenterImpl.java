package com.github.globant.githubsubscribers.subscriberslist.presenter;

import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.github.globant.githubsubscribers.commons.utils.Utils;
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
 * @since 18/08/2016
 */
public class SubscribersListPresenterImpl implements SubscribersListPresenter, SubscribersListInteractor.OnFinishedListener {

    private SubscribersListView view;
    private SubscribersListInteractor interactor;
    private List<Subscriber> subscribersListData;

    public SubscribersListPresenterImpl(SubscribersListView view) {
        this.view = view;
        this.interactor = new SubscribersListInteractorImpl();
        this.subscribersListData = new ArrayList<>();
    }

    public SubscribersListPresenterImpl(SubscribersListView view, List<Subscriber> subscribersListData) {
        this.view = view;
        this.interactor = new SubscribersListInteractorImpl();
        this.subscribersListData = subscribersListData;
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor.onCancelRequest();
    }

    @Override
    public void getSubscribersList() {
        interactor.getSubscribersDataList(this);
    }

    @Override
    public void onResponse(List<Subscriber> listItems) {
        subscribersListData.clear();
        subscribersListData.addAll(listItems);
        view.showSubscribersList(subscribersListData);
    }

    public List<Subscriber> getSubscribersListData() {
        return subscribersListData;
    }

    @Override
    public void onFailure(String errorMessage) {
        //TODO: Manage message errors
        if (view != null) {
            view.showSubscribersError();
        }
    }
}
