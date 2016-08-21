package com.app.advancecarolina;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by neegbeahreeves on 8/19/16.
 */
public class CandidatesFragment extends ListFragment {

    private CandidateListAdapter candidateListAdapter;
    private ListView listView;
    private SwipeRefreshLayout mySwipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_candidates, container, false);
        candidateListAdapter = new CandidateListAdapter(this.getActivity());
        setListAdapter(candidateListAdapter);


        candidateListAdapter.loadObjects();
        return rootView;
    }
}
