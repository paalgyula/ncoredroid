package com.gwsystems.ncoredroid;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gwsystems.ncoredroid.entity.TorrentObject;

import java.util.List;

/**
 * Created by paalgyula on 2014.04.27..
 */
public class TorrentListAdapter extends ArrayAdapter<TorrentObject> {
    private final Activity context;
    private final List<TorrentObject> objects;

    public TorrentListAdapter(Activity context, List<TorrentObject> objects) {
        super(context, R.layout.torrent_list_item_odd, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            if ( position % 2 == 0 )
                rowView = inflater.inflate(R.layout.torrent_list_item_odd, null);
            else
                rowView = inflater.inflate(R.layout.torrent_list_item_even, null);

            // configure view holder
            TorrentItemViewHolder viewHolder = new TorrentItemViewHolder();
            viewHolder.setText((TextView) rowView.findViewById(R.id.torrentListItemLabel));
            viewHolder.setIcon((ImageView) rowView.findViewById(R.id.torrentListItemIcon));
            rowView.setTag(viewHolder);
        }

        // fill data
        TorrentItemViewHolder holder = (TorrentItemViewHolder) rowView.getTag();
        TorrentObject s = objects.get(position);
        holder.getText().setText(s.getName());

        if ( s.getCategory().equalsIgnoreCase("MP3/EN") ) {
            holder.getIcon().setImageResource( R.drawable.ico_mp3 );
        } else if ( s.getCategory().equalsIgnoreCase("PC/ISO") )
            holder.getIcon().setImageResource( R.drawable.ico_game_iso );
        else if ( s.getCategory().equalsIgnoreCase("HD/HU") )
            holder.getIcon().setImageResource( R.drawable.ico_hdser_hun );

        return rowView;
    }
}
