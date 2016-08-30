package com.github.globant.githubsubscribers.subscriberslist;

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
public class SubscribersAdapter extends RecyclerView.Adapter<SubscribersAdapter.ViewHolder> {

    private List<Subscriber> subscriberList;
    private Context context;

    public SubscribersAdapter(Context context){
        this.subscriberList = new ArrayList<>();
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtLoginSubscriber;
        private ImageView imgAvatarSubscriber;

        public ViewHolder(View itemView) {

            super(itemView);
            this.txtLoginSubscriber = (TextView) itemView.findViewById(R.id.txt_login_subscriber);
            this.imgAvatarSubscriber = (ImageView)itemView.findViewById(R.id.img_avatar_subscriber);
        }
    }

    @Override
    public SubscribersAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_subscriber, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubscribersAdapter.ViewHolder viewHolder, int position) {
        viewHolder.txtLoginSubscriber.setText(this.subscriberList.get(position).getLogin());
        Picasso.with(this.context).load(this.subscriberList.get(position).getAvataUrl()).into(viewHolder.imgAvatarSubscriber);
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
}
