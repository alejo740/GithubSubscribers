package com.github.globant.githubsubscribers.subscribersdetail;

import com.github.globant.githubsubscribers.commons.models.Repository;
import com.github.globant.githubsubscribers.commons.models.User;
import com.github.globant.githubsubscribers.commons.utils.Constants;
import com.github.globant.githubsubscribers.commons.utils.Debug;
import com.github.globant.githubsubscribers.commons.utils.ErrorMessagesHelper;
import com.github.globant.githubsubscribers.subscribersdetail.interactor.SubscriberDetailInteractor;
import com.github.globant.githubsubscribers.subscribersdetail.presenter.SubscriberDetailPresenter;
import com.github.globant.githubsubscribers.subscribersdetail.presenter.SubscriberDetailPresenterImpl;
import com.github.globant.githubsubscribers.subscribersdetail.view.SubscriberDetailView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Unit testing for the workflow of MVP of SubscriberDetail going by the methods from view,
 * presenter and interactor.
 *
 * @author edwin.cobos
 * @since 13/09/2016
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Debug.class}) // Prepare the static classes for mocking
public class SubscriberDetailPresenterTest {

    private static final String USER_NAME_PARAM = "keyboardsurfer";
    private static final String PROFILE_HTML_URL = "https://github.com/keyboardsurfer";

    /**
     * Data for testing user info.
     */
    private static String login = "keyboardsurfer";
    private static int id = 336005;
    private static String avatar_url = "https://avatars.githubusercontent.com/u/336005?v=3";
    private static String html_url = "https://github.com/keyboardsurfer";
    private static String name = "Ben Weiss";
    private static String company = "@Google";
    private static String location = "London, UK";
    private static int public_repos = 5;
    private static int followers = 1149;
    private static int following = 55;

    /**
     * Data for testing user repositories list.
     */
    private static String id_repo1 = "5284389";
    private static String name_repo1 = "Crouton";
    private static String fullName_repo1 = "keyboardsurfer/Crouton";
    private static String html_url_repo1 = "https://github.com/keyboardsurfer/Crouton";

    private static String id_repo2 = "10404007";
    private static String name_repo2 = "homebrew-tap";
    private static String fullName_repo2 = "keyboardsurfer/homebrew-tap";
    private static String html_url_repo2 = "https://github.com/keyboardsurfer/homebrew-tap";

    @Mock
    private SubscriberDetailInteractor interactor;

    @Mock
    private SubscriberDetailView view;

    @Captor
    private ArgumentCaptor<SubscriberDetailInteractor.OnFinishedListener> OnFinishedListenerCallback;

    private SubscriberDetailPresenter presenter;

    @Before
    public void setupSubscriberDetailPresenter() {
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        presenter = new SubscriberDetailPresenterImpl(view, interactor);
        // Setup mocking for Environment and File classes
        mockStatic(Debug.class);
    }

    /**
     * Unit Test to try when a successful answer is given from the User Info service
     */
    @Test
    public void getUserInfoFromInteractorAndLoadIntoView() {

        // Given an initialized User object with fake data
        User user = new User(login, id, avatar_url, html_url, name, company, location, public_repos, followers, following);

        // When detail presenter is called to get a User information
        presenter.getUser(USER_NAME_PARAM);

        //Then User information is loaded from Interactor and the callback is captured
        verify(interactor).getUserData(eq(USER_NAME_PARAM), OnFinishedListenerCallback.capture());
        verify(view).toggleProgressIndicator(true);

        OnFinishedListenerCallback.getValue().onFinishedUser(user);

        verify(view).toggleProgressIndicator(false);
        verify(view).showSubscriberDetails(user);
    }

    /**
     * Unit Test to try when a null answer is given from the User Info service
     */
    @Test
    public void getNullUserInfoRequestFromInteractor() {

        // Given a type error with fake error message
        ErrorMessagesHelper.TypeError typeError = ErrorMessagesHelper.TypeError.BAD_ANSWER;
        String errorMessage = Constants.EXCEPTION_ERROR + ": " + typeError.toString();

        // When detail presenter is called to get a User information
        presenter.getUser(USER_NAME_PARAM);

        //Then User information is loaded from Interactor and the callback is captured
        verify(interactor).getUserData(eq(USER_NAME_PARAM), OnFinishedListenerCallback.capture());
        verify(view).toggleProgressIndicator(true);

        OnFinishedListenerCallback.getValue().onFailureUser(errorMessage, typeError);

        verify(view).toggleProgressIndicator(false);
        int messageId = ErrorMessagesHelper.getMessage(typeError);
        verify(view).showUserError(messageId);
    }

    /**
     * Unit Test to try when a Not Connection answer is given from the User Info service
     */
    @Test
    public void getNotConnectionUserInfoRequestFromInteractor() {

        // Given a type error with fake error message
        ErrorMessagesHelper.TypeError typeError = ErrorMessagesHelper.TypeError.NO_CONNECTION;
        String errorMessage = Constants.EXCEPTION_ERROR + ": " + typeError.toString();

        // When detail presenter is called to get a User information
        presenter.getUser(USER_NAME_PARAM);

        //Then User information is loaded from Interactor and the callback is captured
        verify(interactor).getUserData(eq(USER_NAME_PARAM), OnFinishedListenerCallback.capture());
        verify(view).toggleProgressIndicator(true);

        OnFinishedListenerCallback.getValue().onFailureUser(errorMessage, typeError);

        verify(view).toggleProgressIndicator(false);
        int messageId = ErrorMessagesHelper.getMessage(typeError);
        verify(view).showUserError(messageId);
    }

    /**
     * Unit Test to try when a successful answer is given from the Repositories service
     */
    @Test
    public void getRepositoriesListFromInteractorAndLoadIntoView() {

        // Given an initialized Repository object with fake data and a list is created
        Repository repo1 = new Repository(id_repo1, name_repo1, fullName_repo1, html_url_repo1);
        Repository repo2 = new Repository(id_repo2, name_repo2, fullName_repo2, html_url_repo2);
        ArrayList<Repository> repoList = new ArrayList<>();
        repoList.add(repo1);
        repoList.add(repo2);

        // When detail presenter is called to get a Repositories list
        presenter.getRepositoryList(USER_NAME_PARAM);

        //Then Repositories list is loaded from Interactor and the callback is captured
        verify(interactor).getUserRepositoryData(eq(USER_NAME_PARAM), OnFinishedListenerCallback.capture());
        verify(view).toggleProgressIndicator(true);

        OnFinishedListenerCallback.getValue().onFinishedRepository(repoList);

        verify(view).toggleProgressIndicator(false);
        verify(view).showSubscriberUserRepositories(repoList);
    }

    /**
     * Unit Test to try when a Empty answer is given from the Respositories service
     */
    @Test
    public void getEmptyRepositoriesListFromInteractor() {

        // Given an empty Repository object and a type error with fake error message
        ArrayList<Repository> repoList = new ArrayList<>();
        ErrorMessagesHelper.TypeError typeError = ErrorMessagesHelper.TypeError.BAD_ANSWER;
        String errorMessage = Constants.EXCEPTION_ERROR + ": " + typeError.toString();

        // When detail presenter is called to get a Repositories list
        presenter.getRepositoryList(USER_NAME_PARAM);

        //Then Repositories list is loaded from Interactor and the callback is captured
        verify(interactor).getUserRepositoryData(eq(USER_NAME_PARAM), OnFinishedListenerCallback.capture());
        verify(view).toggleProgressIndicator(true);

        OnFinishedListenerCallback.getValue().onFailureRepository(errorMessage, typeError);

        verify(view).toggleProgressIndicator(false);
        int messageId = ErrorMessagesHelper.getMessage(typeError);
        verify(view).showUserError(messageId);
    }

    /**
     * Unit Test to try when a request cancelled answer is given from the Respositories service
     */
    @Test
    public void getCancelledRepositoriesListRequestFromInteractor() {

        // Given an empty Repository object and a type error with fake error message
        ErrorMessagesHelper.TypeError typeError = ErrorMessagesHelper.TypeError.REQUEST_CANCELLED;
        String errorMessage = Constants.EXCEPTION_ERROR + ": " + typeError.toString();

        // When detail presenter is called to get a Repositories list
        presenter.getRepositoryList(USER_NAME_PARAM);

        //Then Repositories list is loaded from Interactor and the callback is captured
        verify(interactor).getUserRepositoryData(eq(USER_NAME_PARAM), OnFinishedListenerCallback.capture());
        verify(view).toggleProgressIndicator(true);

        OnFinishedListenerCallback.getValue().onFailureRepository(errorMessage, typeError);

        verify(view).toggleProgressIndicator(false);
        int messageId = ErrorMessagesHelper.getMessage(typeError);
        verify(view).showUserError(messageId);
    }


}
