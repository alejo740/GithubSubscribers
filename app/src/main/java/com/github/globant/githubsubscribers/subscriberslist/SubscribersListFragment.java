package com.github.globant.githubsubscribers.subscriberslist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.globant.githubsubscribers.R;
import com.github.globant.githubsubscribers.commons.models.Subscriber;

import java.util.List;

/**
 * Fragment class that shows list of Subscribers into RecyclerView
 *
 * @author juan.herrera
 * @since 29/08/2016
 */

public class SubscribersListFragment extends Fragment implements SubscribersListView {

    private SubscribersListPresenter presenter;
    private SubscribersAdapter subscribersAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        subscribersAdapter = new SubscribersAdapter(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFragment = inflater.inflate(R.layout.fragment_subscribers_list, container, false);
        presenter = new SubscribersListPresenterImpl(this);
        presenter.getSubscribersList();
        RecyclerView recyclerViewSubscribers = (RecyclerView) viewFragment.findViewById(R.id.recycler_view_subscribers);
        recyclerViewSubscribers.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewSubscribers.setAdapter(subscribersAdapter);
        return viewFragment;
    }

    @Override
    public void navigateToUserDetail(List<Subscriber> subscriberList) {
        subscribersAdapter.setItems(subscriberList);
    }
}
