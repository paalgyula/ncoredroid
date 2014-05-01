package com.gwsystems.ncoredroid;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by paalgyula on 2014.04.27..
 */
public class TorrentItemViewHolder  {
    private ImageView icon;
    private TextView text;

    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }

    public TextView getText() {
        return text;
    }

    public void setText(TextView text) {
        this.text = text;
    }
}
