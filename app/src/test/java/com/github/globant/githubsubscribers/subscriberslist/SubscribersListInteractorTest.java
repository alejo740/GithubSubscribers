package com.github.globant.githubsubscribers.subscriberslist;

import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.github.globant.githubsubscribers.subscriberslist.interactor.SubscribersListInteractor;
import com.github.globant.githubsubscribers.subscriberslist.interactor.SubscribersListInteractorImpl;
import com.github.globant.githubsubscribers.subscriberslist.presenter.SubscribersListPresenter;
import com.github.globant.githubsubscribers.subscriberslist.presenter.SubscribersListPresenterImpl;
import com.github.globant.githubsubscribers.subscriberslist.view.SubscribersListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit testing for the workflow of MVP of SubscribersList going by the methods from view,
 * presenter and interactor.
 *
 * @author juan.herrera
 * @since 13/09/2016
 */
public class SubscribersListInteractorTest {

    /**
     * Data for testing Subscriber info.
     */
    private static final String login = "";
    private static final String id = "";
    private static final String avataUrl = "";

    @Mock
    private SubscribersListInteractorImpl interactor;

    @Mock
    private SubscribersListView view;

    @Captor
    private ArgumentCaptor<SubscribersListInteractor.OnFinishedListener> OnFinishedListenerCallBack;

    private SubscribersListPresenter presenter;

    @Before
    public void setupSubscriberDetailPresenter() {
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        presenter = new SubscribersListPresenterImpl(view, interactor);
    }

    @Test
    public void getSubcriberListFromInteractorAndLoadIntoView() {

        // Given an initialized Subscriber object with fake data and a list is created
        List<Subscriber> subscriberList = new ArrayList<>(Arrays.asList(new Subscriber(login, id, avataUrl)));

        // When detail presenter is called to get a Subscribers list
        presenter.getSubscribersList();

        //Then Subscribers list is loaded from Interactor and the callback is captured
        verify(interactor).getSubscribersDataList(OnFinishedListenerCallBack.capture());

        //When Subscribers list is finally loaded
        OnFinishedListenerCallBack.getValue().onResponse(subscriberList);

        //Then Subscribers list is shown in UI
        view.showSubscribersList(subscriberList);
    }

}
