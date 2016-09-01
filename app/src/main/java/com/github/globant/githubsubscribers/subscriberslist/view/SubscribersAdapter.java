package com.github.globant.githubsubscribers.subscriberslist.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.globant.githubsubscribers.R;
import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.squareup.picasso.Picasso;

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
    private ItemClickListener clickListener;

    public SubscribersAdapter() {
        this.subscriberList = new ArrayList<>();
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

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public void setItems(List<Subscriber> subscriberList) {
        this.subscriberList.clear();
        this.subscriberList.addAll(subscriberList);
        notifyDataSetChanged();
    }

    public class SubscriberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        SubscriberItem item;

        public SubscriberViewHolder(View itemView) {
            super(itemView);
            item = (SubscriberItem) itemView;
            itemView.setOnClickListener(this);
        }

        public void onBind(Subscriber item) {
            this.item.onBind(item);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onClickItemList((SubscriberItem) view, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onClickItemList(SubscriberItem view, int position);
    }
}
