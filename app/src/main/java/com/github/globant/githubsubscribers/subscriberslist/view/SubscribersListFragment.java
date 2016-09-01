package com.github.globant.githubsubscribers.subscriberslist.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.globant.githubsubscribers.R;
import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.github.globant.githubsubscribers.commons.utils.Utils;
import com.github.globant.githubsubscribers.subscriberslist.presenter.SubscribersListPresenter;
import com.github.globant.githubsubscribers.subscriberslist.presenter.SubscribersListPresenterImpl;

import java.util.List;

/**
 * Fragment class that shows list of Subscribers into RecyclerView
 *
 * @author juan.herrera
 * @since 29/08/2016
 */

public class SubscribersListFragment extends Fragment implements SubscribersListView, SwipeRefreshLayout.OnRefreshListener {

    private SubscribersListPresenter presenter;
    private SubscribersAdapter subscribersAdapter;
    private SwipeRefreshLayout swipeLayout;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        subscribersAdapter = new SubscribersAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFragment = inflater.inflate(R.layout.fragment_subscribers_list, container, false);
        swipeLayout = (SwipeRefreshLayout) viewFragment.findViewById(R.id.swipe_layout);
        swipeLayout.setOnRefreshListener(this);
        presenter = new SubscribersListPresenterImpl(this);
        RecyclerView recyclerViewSubscribers = (RecyclerView) viewFragment.findViewById(R.id.recycler_view_subscribers);
        recyclerViewSubscribers.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewSubscribers.setAdapter(subscribersAdapter);
        return viewFragment;
    }

    private void loadSubscribers(){
        swipeLayout.setRefreshing(true);
        presenter.getSubscribersList();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadSubscribers();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showSubscribersList(List<Subscriber> subscriberList) {
        subscribersAdapter.setItems(subscriberList);
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void showSubscribersError() {
        Toast.makeText(getContext(), R.string.api_client_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToUserDetail() {
        //TODO: Action to change to User details fragment
    }

    @Override
    public void onRefresh() {
        Utils.debugLog("onREFRESHING");
        loadSubscribers();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
