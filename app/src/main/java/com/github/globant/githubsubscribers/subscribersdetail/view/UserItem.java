package com.github.globant.githubsubscribers.subscribersdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.globant.githubsubscribers.R;
import com.github.globant.githubsubscribers.commons.models.User;
import com.squareup.picasso.Picasso;

/**
 * Item class that represents a User Subscriber view
 *
 * @author juan.herrera
 * @since 30/08/2016
 */
public class UserItem extends RelativeLayout{

    private ImageView imgAvatar;
    private TextView txtFullName;
    private TextView txtUserName;
    private TextView txtCompany;
    private TextView txtLocation;
    private TextView txtFollowers;
    private TextView txtFollowingCounter;
    private TextView txtReposCounter;

    public UserItem(Context context) {
        super(context);
        inflateLayout();
    }

    public UserItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout();
    }

    public UserItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayout();
    }

    private void inflateLayout() {
        inflate(this.getContext(), R.layout.row_subscriber, this);

        imgAvatar = (ImageView) findViewById(R.id.img_avatar_subscriber_detail);
        txtFullName = (TextView) findViewById(R.id.txt_full_name_subscriber_detail);
    }

    public void onBind(User item) {

    }
}
