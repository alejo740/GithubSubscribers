package com.github.globant.githubsubscribers.subscriberslist.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.github.globant.githubsubscribers.subscriberslist.presenter.SubscribersListPresenter;
import com.github.globant.githubsubscribers.subscriberslist.presenter.SubscribersListPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment class that shows list of Subscribers into RecyclerView
 *
 * @author juan.herrera
 * @author edwin.cobos
 * @since 29/08/2016
 */

public class SubscribersListFragment extends Fragment implements SubscribersListView, SwipeRefreshLayout.OnRefreshListener, SubscribersAdapter.ItemClickListener {

    private final static String PRESENTER_SUBSCRIBERS_LIST = "PRESENTER_SUBSCRIBERS_LIST";
    private SubscribersListPresenter presenter;
    private SubscribersAdapter subscribersAdapter;
    private SwipeRefreshLayout swipeLayout;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribersAdapter = new SubscribersAdapter();

        if (savedInstanceState != null) {
            List<Subscriber> subscribersListData = savedInstanceState.getParcelableArrayList(PRESENTER_SUBSCRIBERS_LIST);
            presenter = new SubscribersListPresenterImpl(this, subscribersListData);
        } else {
            presenter = new SubscribersListPresenterImpl(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFragment = inflater.inflate(R.layout.fragment_subscribers_list, container, false);
        return viewFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getView() != null) {
            swipeLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_layout);
            swipeLayout.setOnRefreshListener(this);
            RecyclerView recyclerViewSubscribers = (RecyclerView) getView().findViewById(R.id.recycler_view_subscribers);
            recyclerViewSubscribers.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewSubscribers.setAdapter(subscribersAdapter);
            subscribersAdapter.setClickListener(this);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<Subscriber> subscriberListData = (ArrayList<Subscriber>) presenter.getSubscribersListData();
        outState.putParcelableArrayList(PRESENTER_SUBSCRIBERS_LIST, subscriberListData);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClickItemList(Subscriber subscriber) {
        String userName = subscriber.getLogin();
        mListener.onChangeToSubscriberDetails(userName);
    }

    private void loadSubscribers() {
        presenter.getSubscribersList();
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Subscriber> listData = presenter.getSubscribersListData();
        if (listData != null && !listData.isEmpty()) {
            showSubscribersList(listData);
        } else {
            loadSubscribers();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showSubscribersList(List<Subscriber> subscriberList) {
        subscribersAdapter.setItems(subscriberList);
    }

    @Override
    public void showSubscribersError(int messageId) {
        Toast.makeText(getContext(), messageId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void toggleProgress(final boolean active) {
        swipeLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void onRefresh() {
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
        void onChangeToSubscriberDetails(String userName);
    }

}
