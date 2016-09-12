package com.github.globant.githubsubscribers.subscribersdetail.presenter;

import com.github.globant.githubsubscribers.commons.models.Repository;
import com.github.globant.githubsubscribers.commons.models.User;
import com.github.globant.githubsubscribers.commons.utils.Utils;
import com.github.globant.githubsubscribers.subscribersdetail.interactor.SubscriberDetailInteractor;
import com.github.globant.githubsubscribers.subscribersdetail.interactor.SubscriberDetailInteractorImpl;
import com.github.globant.githubsubscribers.subscribersdetail.view.SubscriberDetailView;

import java.util.ArrayList;
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
    private User userData;
    private List<Repository> repositoryListData;

    public SubscriberDetailPresenterImpl(SubscriberDetailView view) {
        this.view = view;
        this.interactor = new SubscriberDetailInteractorImpl();
        this.repositoryListData = new ArrayList<>();
    }

    public SubscriberDetailPresenterImpl(SubscriberDetailView view, User userData, List<Repository> repositoryListData) {
        this.view = view;
        this.interactor = new SubscriberDetailInteractorImpl();
        this.repositoryListData = repositoryListData;
        this.userData = userData;
    }

    public void onFinishedUser(User userItem) {
        userData = userItem;
        view.showSubscriberDetails(userData);
    }

    @Override
    public void onFailureUser(String errorMessage) {
        if(view != null) {
            view.showUserError();
        }
    }

    @Override
    public void onFinishedRepository(List<Repository> repositoryList) {
        repositoryListData.clear();
        repositoryListData.addAll(repositoryList);
        view.showSubscriberUserRepositories(repositoryListData);
    }

    @Override
    public void onFailureRepository(String errorMessage) {
        if(view != null) {
            view.showRepositoryError();
        }
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor.onCancelRequestUser();
        interactor.onCancelRequestRepository();
    }

    @Override
    public void getUser(String userName) {
        interactor.getUserData(userName, this);
    }

    @Override
    public void getRepositoryList(String userName) {
        interactor.getUserRepositoryData(userName, this);
    }

    public User getUserData() {
        return userData;
    }

    public List<Repository> getRepositoryListData() {
        return repositoryListData;
    }
}
