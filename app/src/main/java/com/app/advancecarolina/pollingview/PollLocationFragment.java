package com.app.advancecarolina.pollingview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.advancecarolina.R;

/**
 * Created by neegbeahreeves on 8/23/16.
 */
public class PollLocationFragment extends Fragment {

    public PollLocationFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView =  inflater.inflate(R.layout.fragment_poll_location, container, false);
        return rootView;
    }
}
