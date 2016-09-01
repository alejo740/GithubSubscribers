package com.github.globant.githubsubscribers.subscribersdetail.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.globant.githubsubscribers.R;
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
    private ItemClickListener clickListener;

    public SubscriberRepositoriesAdapter() {
        this.repositoryList = new ArrayList<>();
    }

    @Override
    public SubscriberDetailViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_repository, viewGroup, false);
        return new SubscriberDetailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SubscriberDetailViewHolder viewHolder, int position) {
        viewHolder.onBind(repositoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public void setItems(List<Repository> repositoryList) {
        this.repositoryList.clear();
        this.repositoryList.addAll(repositoryList);
        notifyDataSetChanged();
    }

    public class SubscriberDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView text;
        private String repoUrl;

        public SubscriberDetailViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.txt_repository_subscriber);
        }

        public void onBind(Repository item) {
            text.setText(item.getFullName());
            repoUrl = item.getHtmlUrl();
        }

        public String getRepoUrl() {
            return repoUrl;
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClickItemList(this, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onClickItemList(SubscriberDetailViewHolder view, int position);
    }
}