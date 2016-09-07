package com.github.globant.githubsubscribers.subscriberslist.interactor;

import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.github.globant.githubsubscribers.commons.utils.ApiClientGithub;
import com.github.globant.githubsubscribers.commons.utils.Constants;
import com.github.globant.githubsubscribers.subscriberslist.interactor.SubscribersListInteractor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Interactor class that manages the connection with external sources to get Data.
 * This class implements SubscribersListInteractor interface.
 *
 * @author edwin.cobos
 * @since 19/08/2016
 */
public class SubscribersListInteractorImpl implements SubscribersListInteractor {
    @Override
    public void getSubscribersDataList(final OnFinishedListener listener) {
        Call<List<Subscriber>> call = ApiClientGithub.getApiService().getSubscribers();
        call.enqueue(new Callback<List<Subscriber>>() {
            @Override
            public void onResponse(Call<List<Subscriber>> call, Response<List<Subscriber>> response) {
                if(response.isSuccessful()){
                    List<Subscriber> userList = response.body();
                    listener.onResponse(userList);
                }
                else{
                    listener.onFailure(Constants.MESSAGE_FAILED_SERVICE);
                }
            }

            @Override
            public void onFailure(Call<List<Subscriber>> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}
