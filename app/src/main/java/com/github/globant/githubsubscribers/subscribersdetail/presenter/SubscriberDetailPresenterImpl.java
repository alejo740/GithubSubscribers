package com.github.globant.githubsubscribers.subscribersdetail.presenter;

import com.github.globant.githubsubscribers.commons.models.Repository;
import com.github.globant.githubsubscribers.commons.models.User;
import com.github.globant.githubsubscribers.commons.utils.Utils;
import com.github.globant.githubsubscribers.subscribersdetail.interactor.SubscriberDetailInteractor;
import com.github.globant.githubsubscribers.subscribersdetail.interactor.SubscriberDetailInteractorImpl;
import com.github.globant.githubsubscribers.subscribersdetail.view.SubscriberDetailView;

import java.util.List;

/**
 * Presenter class that connects the Activity class(view) and Interactor class(User Model).
 * This class implements SubscriberDetailPresenter interface.
 *
 * @author juan.herrera
 * @since 30/08/2016
 */
public class SubscriberDetailPresenterImpl implements SubscriberDetailPresenter, SubscriberDetailInteractor.OnFinishedListener {

    private SubscriberDetailView view;
    private SubscriberDetailInteractor interactor;

    public SubscriberDetailPresenterImpl(SubscriberDetailView view) {
        this.view = view;
        this.interactor = new SubscriberDetailInteractorImpl();
    }

    @Override
    public void onFinishedUser(User userItem) {
        view.showSubscriberUser(userItem);
    }

    @Override
    public void onFailureUser(String errorMessage) {
        view.showUserError();
    }

    @Override
    public void onFinishedRepository(List<Repository> repositoryList) {
        view.showSubscriberUserRepository(repositoryList);
    }

    @Override
    public void onFailureRepository(String errorMessage) {
        view.showRepositoryError();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getUser(String userName) {
        interactor.getUserData(this, userName);
    }

    @Override
    public void getRepositoryList(String userName) {
        interactor.getUserRepositoryData(this, userName);
    }
}
