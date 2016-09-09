package com.github.globant.githubsubscribers.subscribersdetail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.globant.githubsubscribers.R;
import com.github.globant.githubsubscribers.commons.models.Repository;
import com.github.globant.githubsubscribers.commons.models.User;
import com.github.globant.githubsubscribers.commons.utils.Utils;
import com.github.globant.githubsubscribers.subscribersdetail.presenter.SubscriberDetailPresenter;
import com.github.globant.githubsubscribers.subscribersdetail.presenter.SubscriberDetailPresenterImpl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment class that shows details of Subscriber
 *
 * @author juan.herrera
 * @since 31/08/2016
 */
public class SubscriberDetailFragment extends Fragment implements SubscriberDetailView, RepositoryAdapter.OnRepositoryItemClickListener, View.OnClickListener {

    private final static String PRESENTER_USER_DATA = "PRESENTER_USER_DATA";
    private final static String PRESENTER_REPO_LIST_DATA = "PRESENTER_REPO_LIST_DATA";
    private final static String ARG_USERNAME = "ARG_USERNAME";
    private final static String HTML_URL = "HTML_URL";
    private String userNameParam;
    private String profileHtmlUrl;

    private ImageView profileImage;
    private TextView profileFullName;
    private TextView profileUserName;
    private TextView profileCompany;
    private TextView profileLocation;
    private TextView profileFollowersCounter;
    private TextView profileFollowingCounter;
    private TextView profileReposCounter;
    private RepositoryAdapter repositoriesAdapter;
    private SubscriberDetailPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        repositoriesAdapter = new RepositoryAdapter();
        if (savedInstanceState != null) {
            User userData = savedInstanceState.getParcelable(PRESENTER_USER_DATA);
            List<Repository> repositoryListData = savedInstanceState.getParcelableArrayList(PRESENTER_REPO_LIST_DATA);
            userNameParam = savedInstanceState.getString(ARG_USERNAME);
            profileHtmlUrl = savedInstanceState.getString(HTML_URL);
            presenter = new SubscriberDetailPresenterImpl(this, userData, repositoryListData);
        } else {
            loadArgs();
            presenter = new SubscriberDetailPresenterImpl(this);
        }
    }

    public static SubscriberDetailFragment newInstance(String userName) {
        SubscriberDetailFragment fragment = new SubscriberDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, userName);
        fragment.setArguments(args);
        return fragment;
    }

    private void loadArgs() {
        if (getArguments() != null) {
            userNameParam = getArguments().getString(ARG_USERNAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFragment = inflater.inflate(R.layout.fragment_subscribers_detail, container, false);
        return viewFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getView() != null) {
            profileImage = (ImageView) getView().findViewById(R.id.img_avatar_subscriber_detail);
            profileFullName = (TextView) getView().findViewById(R.id.txt_full_name_subscriber_detail);
            profileFullName.setOnClickListener(this);
            profileUserName = (TextView) getView().findViewById(R.id.txt_user_name_subscriber_detail);
            profileCompany = (TextView) getView().findViewById(R.id.txt_company_subscriber_detail);
            profileLocation = (TextView) getView().findViewById(R.id.txt_location_subscriber_detail);
            profileFollowingCounter = (TextView) getView().findViewById(R.id.txt_following_counter_subscriber_detail);
            profileFollowersCounter = (TextView) getView().findViewById(R.id.txt_followers_counter_subscriber_detail);
            profileReposCounter = (TextView) getView().findViewById(R.id.txt_repos_counter_subscriber_detail);

            RecyclerView recyclerViewSubscribers = (RecyclerView) getView().findViewById(R.id.list_repositories);
            recyclerViewSubscribers.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewSubscribers.setAdapter(repositoriesAdapter);
            repositoriesAdapter.setClickListener(this);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<Repository> repositoryListData = (ArrayList<Repository>) presenter.getRepositoryListData();
        outState.putParcelableArrayList(PRESENTER_REPO_LIST_DATA, repositoryListData);

        User userData = presenter.getUserData();
        outState.putParcelable(PRESENTER_USER_DATA, userData);

        outState.putString(ARG_USERNAME, userNameParam);
        outState.putString(HTML_URL, profileHtmlUrl);
    }

    @Override
    public void onResume() {
        super.onResume();
        User userData = presenter.getUserData();
        if (userData != null) {
            showSubscriberDetails(userData);
        } else {
            presenter.getUser(userNameParam);
        }

        ArrayList<Repository> repositoryListData = (ArrayList<Repository>) presenter.getRepositoryListData();
        if (repositoryListData != null && !repositoryListData.isEmpty()) {
            showSubscriberUserRepositories(repositoryListData);
        } else {
            presenter.getRepositoryList(userNameParam);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showUserError() {
        Toast.makeText(getContext(), R.string.api_client_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRepositoryError() {
        Toast.makeText(getContext(), R.string.api_client_error, Toast.LENGTH_LONG).show();
    }

    public void showSubscriberDetails(User userInfo) {
        Picasso.with(getContext()).load(userInfo.getAvatarUrl()).into(profileImage);
        profileFullName.setText(Utils.setUnderlineText(userInfo.getName()));
        profileUserName.setText(userInfo.getLogin());
        profileCompany.setText(userInfo.getCompany());
        profileLocation.setText(userInfo.getLocation());
        profileFollowingCounter.setText(String.valueOf(userInfo.getFollowing()));
        profileFollowersCounter.setText(String.valueOf(userInfo.getFollowers()));
        profileReposCounter.setText(String.valueOf(userInfo.getPublicRepos()));
        profileHtmlUrl = userInfo.getHtmlUrl();
    }

    @Override
    public void showSubscriberUserRepositories(List<Repository> repositories) {
        repositoriesAdapter.setItems(repositories);
    }

    @Override
    public void onClickItemList(Repository repository) {
        String repoUrl = repository.getHtmlUrl();
        Utils.openLinkInBrowser(getContext(), repoUrl);
    }

    @Override
    public void onClick(View v) {
        if (profileHtmlUrl != null) {
            Utils.openLinkInBrowser(getContext(), profileHtmlUrl);
        }
    }
}
