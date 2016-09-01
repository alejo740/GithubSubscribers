package com.github.globant.githubsubscribers.subscribersdetail.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.github.globant.githubsubscribers.commons.models.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class that puts user repositories provided by a service
 *
 * @author juan.herrera
 * @since 31/08/2016
 */
public class SubscriberDetailAdapter extends RecyclerView.Adapter<SubscriberDetailAdapter.SubscriberDetailViewHolder> {

    private List<Repository> repositoryList;

    public SubscriberDetailAdapter(){
        this.repositoryList = new ArrayList<>();
    }

    @Override
    public SubscriberDetailViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        SubscriberDetailItem item = new SubscriberDetailItem(viewGroup.getContext());
        return new SubscriberDetailViewHolder(item);
    }

    @Override
    public void onBindViewHolder(SubscriberDetailViewHolder viewHolder, int position) {
        viewHolder.onBind(repositoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    public void setItems(List<Repository> repositoryList){
        this.repositoryList.clear();
        this.repositoryList.addAll(repositoryList);
        notifyDataSetChanged();
    }

    public class SubscriberDetailViewHolder extends RecyclerView.ViewHolder {

        SubscriberDetailItem item;

        public SubscriberDetailViewHolder(View itemView) {

            super(itemView);
            item = (SubscriberDetailItem) itemView;
        }


        public void onBind(Repository item) {
            this.item.onBind(item);
        }
    }
}
