package com.app.advancecarolina;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

/**
 * Created by neegbeahreeves on 8/19/16.
 */
public class NewsFragment extends ListFragment {

    private NewsListViewAdapter newsListViewAdapter;
    private ListView listView;
    private SwipeRefreshLayout mySwipeRefreshLayout;


    @Override
    public void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        // Inflate the layout of the fragment
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        newsListViewAdapter = new NewsListViewAdapter(this.getActivity());
        setListAdapter(newsListViewAdapter);


        newsListViewAdapter.loadObjects();
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }
}
