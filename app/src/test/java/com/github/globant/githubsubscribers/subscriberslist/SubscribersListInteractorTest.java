package com.github.globant.githubsubscribers.subscriberslist;

import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.github.globant.githubsubscribers.commons.utils.Constants;
import com.github.globant.githubsubscribers.commons.utils.Debug;
import com.github.globant.githubsubscribers.commons.utils.ErrorMessagesHelper;
import com.github.globant.githubsubscribers.subscriberslist.interactor.SubscribersListInteractor;
import com.github.globant.githubsubscribers.subscriberslist.interactor.SubscribersListInteractorImpl;
import com.github.globant.githubsubscribers.subscriberslist.presenter.SubscribersListPresenter;
import com.github.globant.githubsubscribers.subscriberslist.presenter.SubscribersListPresenterImpl;
import com.github.globant.githubsubscribers.subscriberslist.view.SubscribersListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

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
@RunWith(PowerMockRunner.class)
@PrepareForTest(Debug.class)
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
    private ArgumentCaptor<SubscribersListInteractor.OnFinishedListener> OnFinishedListenerCallback;

    private SubscribersListPresenter presenter;

    @Before
    public void setupSubscriberDetailPresenter() {
        MockitoAnnotations.initMocks(this);

        // Setup mocking for Debug class
        mockStatic(Debug.class);

        // Get a reference to the class under test
        presenter = new SubscribersListPresenterImpl(view, interactor);

    }

    @Test
    public void getSubcriberListFromInteractorAndLoadIntoView() {

        // Given a initialized Subscriber object with fake data and a list is created
        List<Subscriber> subscriberList = new ArrayList<>(Arrays.asList(new Subscriber(login, id, avataUrl)));

        // When detail presenter is called to get a Subscribers list
        presenter.getSubscribersList();

        // Then Subscribers list is loaded from Interactor and the callback is captured
        verify(interactor).getSubscribersDataList(OnFinishedListenerCallback.capture());
        verify(view).toggleProgress(true);

        OnFinishedListenerCallback.getValue().onResponse(subscriberList);

        verify(view).toggleProgress(false);
        verify(view).showSubscribersList(subscriberList);
    }

    /**
     * Unit test to try when the service is unavailable
     */
    @Test
    public void getNotConnectionSubscribersListRequestFromInteractor() {

        // Given a type error with fake error message
        ErrorMessagesHelper.TypeError typeError = ErrorMessagesHelper.TypeError.NO_CONNECTION;
        String errorMessage = Constants.EXCEPTION_ERROR + ": " + typeError.toString();

        // When detail presenter is called to get a Subscriber list
        presenter.getSubscribersList();

        // Then Subscribers list is loaded from Interactor and the callback is captured
        verify(interactor).getSubscribersDataList(OnFinishedListenerCallback.capture());
        verify(view).toggleProgress(true);

        OnFinishedListenerCallback.getValue().onFailure(errorMessage, typeError);

        int messageId = ErrorMessagesHelper.getMessage(typeError);
        verify(view).toggleProgress(false);
        verify(view).showSubscribersError(messageId);
    }

    /**
     * Unit test to try when the service answers with a empty or null subscriber list
     */
    @Test
    public void getEmptySubscribersListRequestFromInteractor() {

        // Given a type error with fake error message
        ErrorMessagesHelper.TypeError typeError = ErrorMessagesHelper.TypeError.BAD_ANSWER;
        String errorMessage = Constants.EXCEPTION_ERROR + ": " + typeError.toString();

        // When detail presenter is called to get a Subscriber list
        presenter.getSubscribersList();

        // Then Subscribers list is loaded from Interactor and the callback is captured
        verify(interactor).getSubscribersDataList(OnFinishedListenerCallback.capture());
        verify(view).toggleProgress(true);

        OnFinishedListenerCallback.getValue().onFailure(errorMessage, typeError);

        verify(view).toggleProgress(false);
        int messageId = ErrorMessagesHelper.getMessage(typeError);
        verify(view).showSubscribersError(messageId);
    }


}
