package com.github.globant.githubsubscribers.subscribersdetail;

import com.github.globant.githubsubscribers.commons.models.Repository;
import com.github.globant.githubsubscribers.commons.models.User;
import com.github.globant.githubsubscribers.subscribersdetail.interactor.SubscriberDetailInteractor;
import com.github.globant.githubsubscribers.subscribersdetail.presenter.SubscriberDetailPresenter;
import com.github.globant.githubsubscribers.subscribersdetail.presenter.SubscriberDetailPresenterImpl;
import com.github.globant.githubsubscribers.subscribersdetail.view.SubscriberDetailView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * @author edwin.cobos
 * @since 13/09/2016
 */
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
    private static String company = "@Google ";
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
    private ArgumentCaptor<SubscriberDetailInteractor.OnFinishedListener> OnFinishedListenerCallBack;

    private SubscriberDetailPresenter presenter;

    @Before
    public void setupSubscriberDetailPresenter() {
        MockitoAnnotations.initMocks(this);

        presenter = new SubscriberDetailPresenterImpl(view, interactor);
    }

    @Test
    public void getUserInfoFromInteractorAndLoadIntoView() {

        User user = new User(login, id, avatar_url, html_url, name, company, location, public_repos, followers, following);

        presenter.getUser(USER_NAME_PARAM);
        verify(interactor).getUserData(eq(USER_NAME_PARAM), OnFinishedListenerCallBack.capture());

        OnFinishedListenerCallBack.getValue().onFinishedUser(user);

        view.showSubscriberDetails(user);
    }

    @Test
    public void getRepositoriesListFromInteractorAndLoadIntoView() {

        Repository repo1 = new Repository(id_repo1, name_repo1, fullName_repo1, html_url_repo1);
        Repository repo2 = new Repository(id_repo2, name_repo2, fullName_repo2, html_url_repo2);
        ArrayList<Repository> repoList = new ArrayList<>();
        repoList.add(repo1);
        repoList.add(repo2);

        presenter.getRepositoryList(USER_NAME_PARAM);
        verify(interactor).getUserRepositoryData(eq(USER_NAME_PARAM), OnFinishedListenerCallBack.capture());

        OnFinishedListenerCallBack.getValue().onFinishedRepository(repoList);

        view.showSubscriberUserRepositories(repoList);
    }

}
