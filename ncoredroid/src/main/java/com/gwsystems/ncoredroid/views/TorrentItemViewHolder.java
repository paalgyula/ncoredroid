package com.gwsystems.ncoredroid.views;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gwsystems.ncoredroid.R;
import com.gwsystems.ncoredroid.entity.TorrentObject;
import com.gwsystems.ncoredroid.utils.CategoryIcons;

/**
 * Created by paalgyula on 2014.04.27..
 */
public class TorrentItemViewHolder {

    private TorrentObject torrentObject;
    private TextView seed;
    private TextView leech;
    private ImageView icon;
    private TextView text;
    private View textLayout;
    private View mainView;

    public TorrentItemViewHolder(Activity context, TorrentObject torrentObject) {
        mainView = context.getLayoutInflater().inflate(R.layout.torrent_list_item, null);
        this.torrentObject = torrentObject;

        this.prepareFields();
        this.fillObjectsWithParams();
        this.initIcon();
    }

    public View getView() {
        return mainView;
    }

    private void initIcon() {
        icon.setImageResource(CategoryIcons.getIcon(torrentObject.getCategory()));
    }

    private void prepareFields() {
        try {
            this.icon = (ImageView) this.mainView.findViewById(R.id.torrentIcon);
            this.textLayout = this.mainView.findViewById(R.id.textLayout);
            this.text = (TextView) this.mainView.findViewById(R.id.torrentLabel);
            this.seed = (TextView) this.mainView.findViewById(R.id.seedView);
            this.leech = (TextView) this.mainView.findViewById(R.id.leechView);
        } catch (NullPointerException e) {
            Log.e("TorrentItemViewHolder", "Nem talalhato a megadott mezo", e);
        }
    }

    private void fillObjectsWithParams() {
        if (text != null)
            this.text.setText(torrentObject.getName());
        if (leech != null)
            this.leech.setText(torrentObject.getSeed());
        if (seed != null)
            this.seed.setText(torrentObject.getLeech());
    }

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

    public void setEven() {
        textLayout.setBackgroundResource(R.drawable.torrent_item_bg_even);
        icon.setBackgroundResource(R.drawable.torrent_item_bg_even);
    }

    public TorrentObject getTorrent() {
        return torrentObject;
    }
}
