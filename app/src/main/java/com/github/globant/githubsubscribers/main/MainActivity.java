package com.github.globant.githubsubscribers.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.globant.githubsubscribers.R;
import com.github.globant.githubsubscribers.subscribersdetail.view.SubscriberDetailFragment;
import com.github.globant.githubsubscribers.subscriberslist.view.SubscribersListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            SubscribersListFragment subscribersListFragment = new SubscribersListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, subscribersListFragment).commit();
        }
    }
}
