package com.github.globant.githubsubscribers.subscriberslist.interactor;

import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.github.globant.githubsubscribers.commons.utils.ApiClientGithub;
import com.github.globant.githubsubscribers.commons.utils.Constants;
import com.github.globant.githubsubscribers.commons.utils.Debug;
import com.github.globant.githubsubscribers.commons.utils.ErrorMessagesHelper;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Interactor class that manages the connection with external sources to get Data.
 * This class implements SubscribersListInteractor interface.
 *
 * @author edwin.cobos
 * @author juan.herrera
 * @since 19/08/2016
 */
public class SubscribersListInteractorImpl implements SubscribersListInteractor {
    Call<List<Subscriber>> call;

    @Override
    public void getSubscribersDataList(final OnFinishedListener listener) {
        call = ApiClientGithub.getApiService().getSubscribers();
        call.enqueue(new Callback<List<Subscriber>>() {
            @Override
            public void onResponse(Call<List<Subscriber>> call, Response<List<Subscriber>> response) {
                if (response.isSuccessful()) {
                    List<Subscriber> userList = response.body();
                    listener.onResponse(userList);
                } else {
                    try {
                        listener.onFailure(response.errorBody().string(), ErrorMessagesHelper.TypeError.BAD_ANSWER);
                    } catch (IOException e) {
                        Debug.e(Constants.EXCEPTION_ERROR, e);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Subscriber>> call, Throwable t) {
                if (call.isCanceled()) {
                    listener.onFailure(t.getMessage(), ErrorMessagesHelper.TypeError.REQUEST_CANCELLED);
                } else {
                    listener.onFailure(t.getMessage(), ErrorMessagesHelper.TypeError.NO_CONNECTION);
                }
            }
        });
    }

    @Override
    public void onCancelRequest() {
        if (call != null && call.isExecuted()) {
            call.cancel();
        }
    }
}
