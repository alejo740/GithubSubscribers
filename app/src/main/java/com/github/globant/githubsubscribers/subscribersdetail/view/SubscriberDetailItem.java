package com.github.globant.githubsubscribers.subscribersdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.globant.githubsubscribers.R;
import com.github.globant.githubsubscribers.commons.models.Repository;
import com.github.globant.githubsubscribers.commons.models.Subscriber;

/**
 * Item class that represents a single row of user repository
 *
 * @author juan.herrera
 * @since 31/08/2016
 */
public class SubscriberDetailItem extends RelativeLayout {

    private TextView text;

    public SubscriberDetailItem(Context context) {
        super(context);
        inflateLayout();
    }

    public SubscriberDetailItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout();
    }

    public SubscriberDetailItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayout();
    }

    private void inflateLayout() {
        inflate(this.getContext(), R.layout.row_repository, this);
        text = (TextView) findViewById(R.id.txt_repository_subscriber);
    }

    public void onBind(Repository item) {
        text.setText(item.getFullName());
    }
}
