package com.github.globant.githubsubscribers.subscriberslist.interactor;

import com.github.globant.githubsubscribers.commons.models.Subscriber;
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
 * Unit tests for the implementation of {@link SubscribersListInteractorImpl}
 *
 * @author juan.herrera
 * @since 13/09/2016
 */
public class SubscribersListInteractorTest {

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

        presenter = new SubscribersListPresenterImpl(view, interactor);
    }

    @Test
    public void getSubcriberListFromInteractorAndLoadIntoView() {

        List<Subscriber> subscriberList = new ArrayList<>(Arrays.asList(new Subscriber(login, id, avataUrl)));

        presenter.getSubscribersList();
        verify(interactor).getSubscribersDataList(OnFinishedListenerCallBack.capture());
        OnFinishedListenerCallBack.getValue().onResponse(subscriberList);
        view.showSubscribersList(subscriberList);
    }

}
