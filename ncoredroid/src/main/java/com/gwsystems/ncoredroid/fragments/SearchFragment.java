package com.gwsystems.ncoredroid.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gwsystems.ncoredroid.CustomProgressDialog;
import com.gwsystems.ncoredroid.R;
import com.gwsystems.ncoredroid.adapters.TorrentListAdapter;
import com.gwsystems.ncoredroid.entity.TorrentObject;
import com.gwsystems.ncoredroid.requests.TorrentListRequest;

import java.util.ArrayList;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.gwsystems.ncoredroid.fragments.SearchFragment.SearchFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SearchFragment extends Fragment implements TorrentListRequest.TorrentListHolder {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SEARCH_STRING = "searchString";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String searchString;
    private String mParam2;

    private SearchFragmentInteractionListener mListener;
    private TorrentListAdapter torrentListAdapter;
    private CustomProgressDialog progressDialog;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(SEARCH_STRING, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchString = getArguments().getString(SEARCH_STRING);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_torrent_search, container, false);
        this.torrentListAdapter = new TorrentListAdapter(getActivity(), new ArrayList<TorrentObject>());
        ListView torrentListView = (ListView) v.findViewById(R.id.torrentListView);
        torrentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO kepvaltas!
                if (mListener != null)
                    mListener.itemSelected(view);
            }
        });

        torrentListView.setAdapter(this.torrentListAdapter);

        progressDialog = getProgressDialog();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Torrent lista betöltése...");
        progressDialog.show();

        new TorrentListRequest(this).execute(searchString);
        //this.showProgressDialog(getString(R.string.torrent_list_downloading));

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (SearchFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SearchFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface SearchFragmentInteractionListener {
        void itemSelected(View view);
    }

    @Override
    public TorrentListAdapter getTorrentListAdapter() {
        return this.torrentListAdapter;
    }

    @Override
    public CustomProgressDialog getProgressDialog() {
        if ( this.progressDialog == null )
            this.progressDialog = new CustomProgressDialog(this.getActivity());
        return this.progressDialog;
    }
}
