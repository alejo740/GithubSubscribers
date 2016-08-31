package com.github.globant.githubsubscribers.subscriberslist.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.globant.githubsubscribers.R;
import com.github.globant.githubsubscribers.commons.models.Subscriber;
import com.squareup.picasso.Picasso;

/**
 * @author edwin.cobos
 * @since 30/08/2016
 */
public class SubscriberItem extends RelativeLayout {

    private ImageView image;
    private TextView text;

    public SubscriberItem(Context context) {
        super(context);
        inflateLayout();
    }

    public SubscriberItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout();
    }

    public SubscriberItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayout();
    }

    private void inflateLayout() {
        inflate(this.getContext(), R.layout.row_subscriber, this);

        image = (ImageView) findViewById(R.id.img_avatar_subscriber);
        text = (TextView) findViewById(R.id.txt_login_subscriber);
    }

    public void onBind(Subscriber item) {
        text.setText(item.getLogin());
        Picasso.with(getContext()).load(item.getAvataUrl()).into(image);
    }
}
