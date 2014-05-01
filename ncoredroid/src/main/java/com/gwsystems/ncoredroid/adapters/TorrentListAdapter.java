package com.gwsystems.ncoredroid.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gwsystems.ncoredroid.R;
import com.gwsystems.ncoredroid.TorrentItemViewHolder;
import com.gwsystems.ncoredroid.entity.TorrentObject;

import java.util.List;

/**
 * Created by paalgyula on 2014.04.27..
 */
public class TorrentListAdapter extends ArrayAdapter<TorrentObject> {
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
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.torrent_list_item, null);
            if ( position % 2 != 0 ) {
                rowView.findViewById(R.id.textLayout).setBackgroundResource(R.drawable.torrent_item_bg_even);
                rowView.findViewById(R.id.torrentIcon).setBackgroundResource(R.drawable.torrent_item_bg_even);
            }

            // configure view holder
            TorrentItemViewHolder viewHolder = new TorrentItemViewHolder();
            assert rowView != null;
            viewHolder.setText((TextView) rowView.findViewById(R.id.torrentLabel));
            viewHolder.setIcon((ImageView) rowView.findViewById(R.id.torrentIcon));
            rowView.setTag(viewHolder);
        }

        // fill data
        TorrentItemViewHolder holder = (TorrentItemViewHolder) rowView.getTag();
        TorrentObject s = objects.get(position);
        holder.getText().setText(s.getName());

        // MP3
        if ( s.getCategory().equalsIgnoreCase("MP3/EN") ) {
            holder.getIcon().setImageResource( R.drawable.ico_mp3 );

        } else if ( s.getCategory().equalsIgnoreCase("PC/ISO") )
            holder.getIcon().setImageResource( R.drawable.ico_game_iso );

        // HD
        else if ( s.getCategory().equalsIgnoreCase("HD/HU") )
            holder.getIcon().setImageResource( R.drawable.ico_hd_hun );
        else if ( s.getCategory().equalsIgnoreCase("HD/EN") )
            holder.getIcon().setImageResource( R.drawable.ico_hdser );

        else if (s.getCategory().equalsIgnoreCase("Lossless/HU"))
            holder.getIcon().setImageResource( R.drawable.ico_lossless_hun );

            // XviD
        else if ( s.getCategory().equalsIgnoreCase("XviD/EN") )
            holder.getIcon().setImageResource( R.drawable.ico_xvidser );

        return rowView;
    }
}
