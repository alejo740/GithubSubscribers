package com.github.globant.githubsubscribers.subscribersdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.globant.githubsubscribers.R;
import com.github.globant.githubsubscribers.commons.models.Repository;

/**
 * @author edwin.cobos
 * @since 01/09/2016
 */
public class RepositoryItem extends RelativeLayout {

    private TextView text;

    public RepositoryItem(Context context) {
        super(context);
        inflateLayout();
    }

    public RepositoryItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout();
    }

    public RepositoryItem(Context context, AttributeSet attrs, int defStyleAttr) {
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
