package com.github.globant.githubsubscribers.commons.utils;

import com.github.globant.githubsubscribers.commons.models.Repository;
import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.github.globant.githubsubscribers.commons.models.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * ApiClientGithub class that works as a singleton to provide a Retrofit instance of Git Api and their end-points
 *
 * @author edwin.cobos
 * @author juan.herrera
 *
 * @since 19/08/2016
 */
public class ApiClientGithub {
    private static GithubApiInterface apiService;

    public static GithubApiInterface getApiService() {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.GITHUB_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(GithubApiInterface.class);
        }
        return apiService;
    }

    /**
     * This interface defines the end-points of service
     */
    public interface GithubApiInterface {

        @GET("repos/googlesamples/android-architecture/subscribers")
        Call<List<Subscriber>> getSubscribers();

        @GET("users/{user}")
        Call<User> getSubscriberUser(@Path("user") String userName);

        @GET("users/{user}/repos")
        Call<List<Repository>> getUserRepositories(@Path("user") String userName);

    }

}
