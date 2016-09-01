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

import com.github.globant.githubsubscribers.R;
import com.github.globant.githubsubscribers.commons.models.Repository;
import com.github.globant.githubsubscribers.commons.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Fragment class that shows details of Subscribers
 *
 * @author juan.herrera
 * @since 31/08/2016
 */
public class SubscriberDetailFragment extends Fragment implements SubscriberDetailView{

    private static final String ARG_USERNAME = "ARG_USERNAME";
    private String userNameArg;

    private ImageView profileImage;
    private TextView profileFullName;
    private TextView profileUserName;
    private TextView profileCompany;
    private TextView profileLocation;
    private TextView profileFollowersCounter;
    private TextView profileFollowingCounter;
    private TextView profileReposCounter;
    private SubscriberRepositoriesAdapter repositoriesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        loadArgs();
        repositoriesAdapter = new SubscriberRepositoriesAdapter();
    }

    public static SubscriberDetailFragment newInstance(String param1) {
        SubscriberDetailFragment fragment = new SubscriberDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, param1);
        fragment.setArguments(args);
        return fragment;
    }

    private void loadArgs() {
        if (getArguments() != null) {
            userNameArg = getArguments().getString(ARG_USERNAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFragment = inflater.inflate(R.layout.fragment_subscribers_detail, container, false);

        profileImage = (ImageView) viewFragment.findViewById(R.id.img_avatar_subscriber_detail);
        profileFullName = (TextView) viewFragment.findViewById(R.id.txt_full_name_subscriber_detail);
        profileUserName = (TextView) viewFragment.findViewById(R.id.txt_user_name_subscriber_detail);
        profileCompany = (TextView) viewFragment.findViewById(R.id.txt_company_subscriber_detail);
        profileLocation = (TextView) viewFragment.findViewById(R.id.txt_location_subscriber_detail);
        profileFollowingCounter = (TextView) viewFragment.findViewById(R.id.txt_following_counter_subscriber_detail);
        profileFollowersCounter = (TextView) viewFragment.findViewById(R.id.txt_followers_counter_subscriber_detail);
        profileReposCounter = (TextView) viewFragment.findViewById(R.id.txt_repos_counter_subscriber_detail);

        RecyclerView recyclerViewSubscribers = (RecyclerView) viewFragment.findViewById(R.id.list_repositories);
        recyclerViewSubscribers.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewSubscribers.setAdapter(repositoriesAdapter);
        return viewFragment;
    }

    @Override
    public void showSubscriberDetails(User userInfo) {
        Picasso.with(getContext()).load(userInfo.getAvatarUrl()).into(profileImage);
        profileFullName.setText(userInfo.getName());
        profileUserName.setText(userInfo.getLogin());
        profileCompany.setText(userInfo.getCompany());
        profileLocation.setText(userInfo.getLocation());
        profileFollowingCounter.setText(userInfo.getFollowing());
        profileFollowersCounter.setText(userInfo.getFollowers());
        profileReposCounter.setText(userInfo.getPublicRepos());
    }

    @Override
    public void showSubscriberRepositories(List<Repository> repositories) {
        repositoriesAdapter.setItems(repositories);
    }
}
