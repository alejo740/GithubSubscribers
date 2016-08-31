package com.github.globant.githubsubscribers.subscribersdetail.presenter;

import com.github.globant.githubsubscribers.commons.models.User;
import com.github.globant.githubsubscribers.commons.utils.Utils;
import com.github.globant.githubsubscribers.subscribersdetail.interactor.UserInteractor;
import com.github.globant.githubsubscribers.subscribersdetail.interactor.UserInteractorImpl;
import com.github.globant.githubsubscribers.subscribersdetail.view.UserView;

import java.util.List;

/**
 * Presenter class that connects the Activity class(view) and Interactor class(User Model).
 * This class implements UserPresenter interface.
 *
 * @author juan.herrera
 * @since 30/08/2016
 */
public class UserPresenterImpl implements UserPresenter, UserInteractor.OnFinishedListener {

    private UserView view;
    private UserInteractor interactor;

    public UserPresenterImpl(UserView view) {
        this.view = view;
        this.interactor = new UserInteractorImpl();
    }

    @Override
    public void onFinished(List<User> listItems) {
        view.showSubscriberUser(listItems);
    }

    @Override
    public void onFailed(String errorMessage) {
        Utils.debugLog(errorMessage);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getSubscribersList(String userName) {
        interactor.getUserDataList(this, userName);
    }
}
