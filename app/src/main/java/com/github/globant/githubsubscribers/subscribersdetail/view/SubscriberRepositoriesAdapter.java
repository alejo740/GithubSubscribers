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
 * @author edwin.cobos
 * @since 31/08/2016
 */
public class SubscriberRepositoriesAdapter extends RecyclerView.Adapter<SubscriberRepositoriesAdapter.SubscriberDetailViewHolder> {

    private List<Repository> repositoryList;

    public SubscriberRepositoriesAdapter(){
        this.repositoryList = new ArrayList<>();
    }

    @Override
    public SubscriberDetailViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RepositoryItem item = new RepositoryItem(viewGroup.getContext());
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

        RepositoryItem item;

        public SubscriberDetailViewHolder(View itemView) {

            super(itemView);
            item = (RepositoryItem) itemView;
        }


        public void onBind(Repository item) {
            this.item.onBind(item);
        }
    }
}