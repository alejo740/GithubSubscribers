package com.github.globant.githubsubscribers.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.github.globant.githubsubscribers.R;
import com.github.globant.githubsubscribers.subscribersdetail.view.SubscriberDetailFragment;
import com.github.globant.githubsubscribers.subscriberslist.view.SubscribersListFragment;

public class MainActivity extends AppCompatActivity implements SubscribersListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            SubscribersListFragment subscribersListFragment = new SubscribersListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, subscribersListFragment).commit();
        }
    }

    @Override
    public void onChangeToSubscriberDetails(String userName) {
        SubscriberDetailFragment subscriberDetailFragment = SubscriberDetailFragment.newInstance(userName);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(R.id.fragment_container, subscriberDetailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
