package com.github.globant.githubsubscribers.subscribersdetail.interactor;

import com.github.globant.githubsubscribers.commons.models.User;
import com.github.globant.githubsubscribers.commons.utils.ApiClientGithub;

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
    public void getUserData(final OnFinishedListener listener, String userName) {
        Call<User> call = ApiClientGithub.getApiService().getSubscriberUser(userName);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userItem = response.body();
                listener.onFinished(userItem);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });
    }
}
