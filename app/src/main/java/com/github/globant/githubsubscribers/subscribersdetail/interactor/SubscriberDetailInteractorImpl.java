package com.github.globant.githubsubscribers.subscribersdetail.interactor;

import com.github.globant.githubsubscribers.commons.models.Repository;
import com.github.globant.githubsubscribers.commons.models.User;
import com.github.globant.githubsubscribers.commons.utils.ApiClientGithub;
import com.github.globant.githubsubscribers.commons.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Interactor class that manages the connection with external sources to get Data.
 * This class implements SubscriberDetailInteractor interface.
 *
 * @author juan.herrera
 * @since 30/08/2016
 */
public class SubscriberDetailInteractorImpl implements SubscriberDetailInteractor {
    private Call<User> callUser;
    private Call<List<Repository>> callRepositories;

    @Override
    public void getUserData(String userName, final OnFinishedListener listener) {
        callUser = ApiClientGithub.getApiService().getSubscriberUser(userName);
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User userItem = response.body();
                    listener.onFinishedUser(userItem);
                } else {
                    listener.onFailureUser(Constants.MESSAGE_FAILED_SERVICE);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailureUser(t.getMessage());
            }
        });
    }

    @Override
    public void getUserRepositoryData(String userName, final OnFinishedListener listener) {
        callRepositories = ApiClientGithub.getApiService().getUserRepositories(userName);
        callRepositories.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if (response.isSuccessful()) {
                    List<Repository> repositoryList = response.body();
                    listener.onFinishedRepository(repositoryList);
                } else {
                    listener.onFailureRepository(Constants.MESSAGE_FAILED_SERVICE);
                }

            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                listener.onFailureRepository(t.getMessage());
            }
        });
    }

    @Override
    public void onCancelRequestUser() {
        if (callUser != null && callUser.isExecuted()) {
            callUser.cancel();
        }
    }

    @Override
    public void onCancelRequestRepository() {
        if (callRepositories != null && callRepositories.isExecuted()) {
            callRepositories.cancel();
        }
    }
}
