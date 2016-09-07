package com.github.globant.githubsubscribers.subscribersdetail.view;

import android.os.Bundle;
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

import java.util.List;

/**
 * Fragment class that shows details of Subscriber
 *
 * @author juan.herrera
 * @since 31/08/2016
 */
public class SubscriberDetailFragment extends Fragment implements SubscriberDetailView, RepositoryAdapter.OnRepositoryItemClickListener, View.OnClickListener {

    private static final String ARG_USERNAME = "ARG_USERNAME";
    private String userNameParam;

    private ImageView profileImage;
    private TextView profileFullName;
    private TextView profileUserName;
    private TextView profileCompany;
    private TextView profileLocation;
    private TextView profileFollowersCounter;
    private TextView profileFollowingCounter;
    private TextView profileReposCounter;
    private String profileHtmlUrl;
    private RepositoryAdapter repositoriesAdapter;
    private View viewFragment;

    private SubscriberDetailPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadArgs();
        if (savedInstanceState == null) {
            setRetainInstance(true);
            repositoriesAdapter = new RepositoryAdapter();
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
        if (savedInstanceState == null) {
            viewFragment = inflater.inflate(R.layout.fragment_subscribers_detail, container, false);

            profileImage = (ImageView) viewFragment.findViewById(R.id.img_avatar_subscriber_detail);
            profileFullName = (TextView) viewFragment.findViewById(R.id.txt_full_name_subscriber_detail);
            profileFullName.setOnClickListener(this);
            profileUserName = (TextView) viewFragment.findViewById(R.id.txt_user_name_subscriber_detail);
            profileCompany = (TextView) viewFragment.findViewById(R.id.txt_company_subscriber_detail);
            profileLocation = (TextView) viewFragment.findViewById(R.id.txt_location_subscriber_detail);
            profileFollowingCounter = (TextView) viewFragment.findViewById(R.id.txt_following_counter_subscriber_detail);
            profileFollowersCounter = (TextView) viewFragment.findViewById(R.id.txt_followers_counter_subscriber_detail);
            profileReposCounter = (TextView) viewFragment.findViewById(R.id.txt_repos_counter_subscriber_detail);

            RecyclerView recyclerViewSubscribers = (RecyclerView) viewFragment.findViewById(R.id.list_repositories);
            recyclerViewSubscribers.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewSubscribers.setAdapter(repositoriesAdapter);
            repositoriesAdapter.setClickListener(this);
        }
        return viewFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getUser(userNameParam);
        presenter.getRepositoryList(userNameParam);
    }

    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showUserError(int messageId) {
        Toast.makeText(getContext(), messageId, Toast.LENGTH_LONG).show();
    }

    public void showSubscriberDetails(User userInfo) {
        Picasso.with(getContext()).load(userInfo.getAvatarUrl()).into(profileImage);
        profileFullName.setText(userInfo.getName());
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
