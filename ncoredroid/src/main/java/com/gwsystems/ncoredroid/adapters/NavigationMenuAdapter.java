package com.gwsystems.ncoredroid.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.gwsystems.ncoredroid.R;

import java.util.List;

/**
 * Created by paalgyula on 2014.04.30..
 */
public class NavigationMenuAdapter extends ArrayAdapter<Object> {

    private final Activity context;

    public NavigationMenuAdapter(Activity context, List<Object> objects) {
        super(context, R.layout.torrent_list_item_odd, objects);
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = context.getLayoutInflater();

        if ( i == 0 ) {
            return inflater.inflate(R.layout.search_input_layout, null);
        }

        return inflater.inflate(R.layout.torrent_list_item_odd, null);
    }
}