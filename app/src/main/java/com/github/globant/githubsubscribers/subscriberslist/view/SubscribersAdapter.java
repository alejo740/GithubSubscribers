package com.github.globant.githubsubscribers.subscriberslist.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.github.globant.githubsubscribers.commons.models.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class that puts data of subscribers list provided by a service
 *
 * @author juan.herrera
 * @since 29/08/2016
 */
public class SubscribersAdapter extends RecyclerView.Adapter<SubscribersAdapter.SubscriberViewHolder> {

    private List<Subscriber> subscriberList;
    private Context context;

    public SubscribersAdapter(Context context){
        this.subscriberList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public SubscriberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        SubscriberItem item = new SubscriberItem(viewGroup.getContext());
        return new SubscriberViewHolder(item);
    }

    @Override
    public void onBindViewHolder(SubscriberViewHolder viewHolder, int position) {
        viewHolder.onBind(subscriberList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.subscriberList.size();
    }

    public void setItems(List<Subscriber> subscriberList){
        this.subscriberList.clear();
        this.subscriberList.addAll(subscriberList);
        notifyDataSetChanged();
    }

    public class SubscriberViewHolder extends RecyclerView.ViewHolder {

        SubscriberItem item;

        public SubscriberViewHolder(View itemView) {

            super(itemView);
            item = (SubscriberItem) itemView;
        }


        public void onBind(Subscriber item) {
            this.item.onBind(item);
        }
    }
}
