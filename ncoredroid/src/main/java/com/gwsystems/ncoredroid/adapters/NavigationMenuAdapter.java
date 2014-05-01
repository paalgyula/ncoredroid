package com.gwsystems.ncoredroid.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.gwsystems.ncoredroid.R;

/**
 * Created by paalgyula on 2014.04.30..
 */
public class NavigationMenuAdapter extends ArrayAdapter<Object> {

    private final Activity context;

    public NavigationMenuAdapter(Activity context) {
        super(context, R.layout.torrent_list_item);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = context.getLayoutInflater();
        return inflater.inflate(R.layout.torrent_list_item, null);
    }

}