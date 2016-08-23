package com.app.advancecarolina.pollingview;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.advancecarolina.NewsListViewAdapter;
import com.app.advancecarolina.R;

/**
 * Created by neegbeahreeves on 8/23/16.
 */
public class ScheduleVoteActivity extends ListFragment {

    public void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        // Inflate the layout of the fragment
        View rootView = inflater.inflate(R.layout.fragment_poll_schedule, container, false);
        //newsListViewAdapter = new NewsListViewAdapter(this.getActivity());
        //setListAdapter(newsListViewAdapter);


       // newsListViewAdapter.loadObjects();
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

}
