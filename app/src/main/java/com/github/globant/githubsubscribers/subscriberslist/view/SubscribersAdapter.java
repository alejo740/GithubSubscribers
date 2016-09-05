package com.github.globant.githubsubscribers.subscriberslist.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.globant.githubsubscribers.R;
import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.github.globant.githubsubscribers.commons.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class that puts data of subscribers list provided by a service
 *
 * @author juan.herrera
 * @author edwin.cobos
 * @since 29/08/2016
 */
public class SubscribersAdapter extends RecyclerView.Adapter<SubscribersAdapter.SubscriberViewHolder> {

    public interface ItemClickListener {
        void onClickItemList(SubscriberViewHolder view, int position);
    }

    private List<Subscriber> subscriberList;
    private ItemClickListener clickListener;

    public SubscribersAdapter() {
        this.subscriberList = new ArrayList<>();
    }

    @Override
    public SubscriberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_subscriber, viewGroup, false);
        return new SubscriberViewHolder(itemView);
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

        private ImageView image;
        private TextView text;

        public SubscriberViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_avatar_subscriber);
            text = (TextView) itemView.findViewById(R.id.txt_login_subscriber);
            itemView.setOnClickListener(this);
        }

        public void onBind(Subscriber item) {
            text.setText(item.getLogin());
            Picasso.with(image.getContext()).load(item.getAvataUrl()).into(image);
        }

        public String getUserName() {
            return text.getText().toString();
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onClickItemList(this, getAdapterPosition());
            }
        }
    }
}
