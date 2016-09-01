package com.github.globant.githubsubscribers.subscriberslist.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.globant.githubsubscribers.R;
import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.github.globant.githubsubscribers.subscriberslist.presenter.SubscribersListPresenter;
import com.github.globant.githubsubscribers.subscriberslist.presenter.SubscribersListPresenterImpl;

import java.util.List;

/**
 * Fragment class that shows list of Subscribers into RecyclerView
 *
 * @author juan.herrera
 * @since 29/08/2016
 */

public class SubscribersListFragment extends Fragment implements SubscribersListView, SubscribersAdapter.ItemClickListener {

    private SubscribersListPresenter presenter;
    private SubscribersAdapter subscribersAdapter;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribersAdapter = new SubscribersAdapter();
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
        subscribersAdapter.setClickListener(this);
        return viewFragment;
    }

    @Override
    public void showSubscribersList(List<Subscriber> subscriberList) {
        subscribersAdapter.setItems(subscriberList);
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
    public void onClickItemList(SubscribersAdapter.SubscriberViewHolder view, int position) {
        mListener.onChangeToSubscriberDetails(view.getUserName());
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onChangeToSubscriberDetails(String userName);
    }

}
