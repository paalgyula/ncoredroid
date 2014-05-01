package com.gwsystems.ncoredroid.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gwsystems.ncoredroid.R;
import com.gwsystems.ncoredroid.entity.TorrentObject;
import com.gwsystems.ncoredroid.utils.CategoryIcons;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class TorrentDetailsFragment extends Fragment {
    private static final String TORRENT = "torrent";

    private TorrentObject torrent;

    TextView torrentName;
    ImageView torrentIcon;

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
        View view = inflater.inflate(R.layout.fragment_torrent_details, container, false);
        this.torrentName = (TextView) view.findViewById(R.id.torrentName);
        this.torrentIcon = (ImageView) view.findViewById(R.id.torrentIcon);

        setupFields();
        setupActions();

        return view;
    }

    private void setupFields() {
        this.torrentName.setText( torrent.getName() );
        this.torrentIcon.setImageResource(CategoryIcons.getIcon(torrent.getCategory()));
    }

    private void setupActions() {
        this.torrentIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.wtf("TorrentDetailsFragment", "Torrent letoltes kattintva!");
            }
        });
    }

    public static TorrentDetailsFragment newInstance(TorrentObject torrentObject) {
        TorrentDetailsFragment fragment = new TorrentDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(TORRENT, torrentObject);
        fragment.setArguments(args);
        return fragment;
    }
}
