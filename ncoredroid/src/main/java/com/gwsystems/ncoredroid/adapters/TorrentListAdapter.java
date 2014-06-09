package com.gwsystems.ncoredroid.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.gwsystems.ncoredroid.R;
import com.gwsystems.ncoredroid.entity.TorrentObject;
import com.gwsystems.ncoredroid.views.TorrentItemViewHolder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by paalgyula on 2014.04.27..
 */
public class TorrentListAdapter extends ArrayAdapter<TorrentObject> implements Serializable {
    private final Activity context;
    private final List<TorrentObject> objects;

    public TorrentListAdapter(Activity context, List<TorrentObject> objects) {
        super(context, R.layout.torrent_list_item, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            TorrentItemViewHolder holder = new TorrentItemViewHolder(context, objects.get(position));
            if (position % 2 != 0)
                holder.setEven();

            rowView = holder.getView();
            rowView.setTag(holder);
        }

        return rowView;
    }

    /**
     * Returns torrent list
     *
     * @return
     */
    public List<TorrentObject> getTorrents() {
        return this.objects;
    }
}
