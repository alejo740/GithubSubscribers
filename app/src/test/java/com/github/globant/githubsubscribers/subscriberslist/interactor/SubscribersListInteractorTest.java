package com.github.globant.githubsubscribers.subscriberslist.interactor;

import android.os.Environment;

import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.github.globant.githubsubscribers.subscriberslist.presenter.SubscribersListPresenterImpl;
import com.github.globant.githubsubscribers.subscriberslist.view.SubscribersListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for the implementation of {@link SubscribersListInteractorImpl}
 *
 * @author juan.herrera
 * @since 13/09/2016
 */
public class SubscribersListInteractorTest {

    @Mock
    private Call<List<Subscriber>> call;

    private SubscribersListInteractorImpl interactor;
    private SubscribersListPresenterImpl presenter;
    private SubscribersListView view;

    @Before
    public void createInstanceForInteractor() {
        interactor = new SubscribersListInteractorImpl();
    }

    @Test
    public void getSubscriberList() {
        presenter = new SubscribersListPresenterImpl(view);
        interactor.getSubscribersDataList(presenter);
        assertThat(interactor.call, is(notNullValue()));
    }

}
