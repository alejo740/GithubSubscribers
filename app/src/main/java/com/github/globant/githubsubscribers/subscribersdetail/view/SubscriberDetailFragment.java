package com.github.globant.githubsubscribers.subscribersdetail.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.globant.githubsubscribers.R;
import com.github.globant.githubsubscribers.commons.models.User;
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
public class SubscriberDetailFragment extends Fragment implements SubscriberDetailView {

    private ImageView imgAvatar;
    private TextView txtFullName;
    private TextView txtUserName;
    private TextView txtCompany;
    private TextView txtLocation;
    private TextView txtFollowingCounter;
    private TextView txtReposCounter;

    private SubscriberDetailPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFragment = inflater.inflate(R.layout.fragment_subscribers_detail, container, false);
        presenter = new SubscriberDetailPresenterImpl(this);
        presenter.getUser("keyboardsurfer");
        imgAvatar = (ImageView) viewFragment.findViewById(R.id.img_avatar_subscriber_detail);
        txtFullName = (TextView) viewFragment.findViewById(R.id.txt_full_name_subscriber_detail);
        txtUserName = (TextView) viewFragment.findViewById(R.id.txt_user_name_subscriber_detail);
        txtCompany = (TextView) viewFragment.findViewById(R.id.txt_company_subscriber_detail);
        txtLocation = (TextView) viewFragment.findViewById(R.id.txt_location_subscriber_detail);
        txtFollowingCounter = (TextView) viewFragment.findViewById(R.id.txt_following_counter_subscriber_detail);
        txtReposCounter = (TextView) viewFragment.findViewById(R.id.txt_repos_counter_subscriber_detail);
        return viewFragment;
    }

    @Override
    public void showSubscriberUser(User subscriberUser) {
        Picasso.with(getContext()).load(subscriberUser.getAvatarUrl()).into(imgAvatar);
        txtFullName.setText(subscriberUser.getName());
        txtUserName.setText(subscriberUser.getLogin());
        txtCompany.setText(subscriberUser.getCompany());
        txtLocation.setText(subscriberUser.getLocation());
        txtFollowingCounter.setText(String.valueOf(subscriberUser.getFollowers()));
        txtReposCounter.setText(String.valueOf(subscriberUser.getPublicRepos()));
    }

    @Override
    public void showSubscribersError() {
        Toast.makeText(getContext(), R.string.api_client_error, Toast.LENGTH_LONG).show();
    }
}
