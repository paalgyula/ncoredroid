package com.gwsystems.ncoredroid.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gwsystems.ncoredroid.R;
import com.gwsystems.ncoredroid.entity.TorrentObject;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class TorrentDetailsFragment extends Fragment {
    private static final String TORRENT = "torrent";

    private TorrentObject torrent;

    public TorrentDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.torrent = (TorrentObject)getArguments().getSerializable(TORRENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_torrent_details, container, false);
    }

    public static TorrentDetailsFragment newInstance(TorrentObject torrentObject) {
        TorrentDetailsFragment fragment = new TorrentDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(TORRENT, torrentObject);
        fragment.setArguments(args);
        return fragment;
    }
}
