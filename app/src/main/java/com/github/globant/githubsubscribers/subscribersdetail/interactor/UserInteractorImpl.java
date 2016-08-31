package com.github.globant.githubsubscribers.subscribersdetail.interactor;

import com.github.globant.githubsubscribers.commons.models.User;
import com.github.globant.githubsubscribers.commons.utils.ApiClientGithub;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Interactor class that manages the connection with external sources to get Data.
 * This class implements UserInteractor interface.
 *
 * @author juan.herrera
 * @since 30/08/2016
 */
public class UserInteractorImpl implements UserInteractor {
    @Override
    public void getUserDataList(final OnFinishedListener listener, String userName) {
        Call<List<User>> call = ApiClientGithub.getApiService().getSubscriberUser(userName);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();
                listener.onFinished(userList);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });
    }
}
