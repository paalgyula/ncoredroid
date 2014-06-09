package com.gwsystems.ncoredroid.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gwsystems.ncoredroid.R;
import com.gwsystems.ncoredroid.entity.TorrentObject;
import com.gwsystems.ncoredroid.requests.DownloadRequest;
import com.gwsystems.ncoredroid.utils.CategoryIcons;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class TorrentDetailsFragment extends Fragment {
    private static final String TORRENT = "torrent";

    private TorrentObject torrent;

    private TextView torrentName;
    private ImageView torrentIcon;
    private TextView uploaded;
    private TextView seeders;
    private TextView downloads;
    private TextView leechers;
    private ImageView downloadBtn;

    public TorrentDetailsFragment() {
        // Required empty public constructor
    }

    public static TorrentDetailsFragment newInstance(TorrentObject torrentObject) {
        TorrentDetailsFragment fragment = new TorrentDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(TORRENT, torrentObject);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.torrent = (TorrentObject) getArguments().getSerializable(TORRENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_torrent_details, container, false);
        this.torrentName = (TextView) view.findViewById(R.id.torrentName);
        this.torrentIcon = (ImageView) view.findViewById(R.id.torrentIcon);
        this.downloadBtn = (ImageView) view.findViewById(R.id.downloadBtn);

        this.uploaded = (TextView) view.findViewById(R.id.torrentUploaded);
        this.downloads = (TextView) view.findViewById(R.id.torrentDownloads);
        this.leechers = (TextView) view.findViewById(R.id.torrentLeechers);
        this.seeders = (TextView) view.findViewById(R.id.torrentSeeders);

        setupFields();
        setupActions();

        return view;
    }

    private void setupFields() {
        this.torrentName.setText(torrent.getName());
        this.torrentIcon.setImageResource(CategoryIcons.getIcon(torrent.getCategory()));
        this.uploaded.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        this.seeders.setText(torrent.getSeed());
        this.leechers.setText(torrent.getLeech());
        this.downloads.setText(torrent.getDownloaded());
    }

    private void setupActions() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = "https://ncore.cc/" + torrent.getLink().replace("details", "download");
                Toast.makeText(getActivity(), "Letöltés megkezdve...", Toast.LENGTH_SHORT);
                new DownloadRequest(getActivity()).execute(link, torrent.getName(), torrent.getName().replaceAll(" ", "_"));
            }
        };

        this.downloadBtn.setOnClickListener(listener);
        this.torrentIcon.setOnClickListener(listener);
    }
}
