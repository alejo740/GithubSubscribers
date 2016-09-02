package com.github.globant.githubsubscribers.subscribersdetail.interactor;

import com.github.globant.githubsubscribers.commons.models.Repository;
import com.github.globant.githubsubscribers.commons.models.User;
import com.github.globant.githubsubscribers.commons.utils.ApiClientGithub;

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
    @Override
    public void getUserData(String userName, final OnFinishedListener listener) {
        Call<User> call = ApiClientGithub.getApiService().getSubscriberUser(userName);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userItem = response.body();
                listener.onFinishedUser(userItem);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailureUser(t.getMessage());
            }
        });
    }

    @Override
    public void getUserRepositoryData(String userName, final OnFinishedListener listener) {
        Call<List<Repository>> call = ApiClientGithub.getApiService().getUserRepositories(userName);
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                List<Repository> repositoryList = response.body();
                listener.onFinishedRepository(repositoryList);
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                listener.onFailureRepository(t.getMessage());
            }
        });
    }
}
