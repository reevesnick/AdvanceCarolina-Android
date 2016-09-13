package com.app.advancecarolina;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by neegbeahreeves on 8/19/16.
 */
public class BallotFragment extends ListFragment {

    public BallotFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View rootview =  inflater.inflate(R.layout.fragment_ballot, container, false);
        /*WebView myWebView = (WebView) rootview.findViewById(R.id.webView);
        myWebView.loadUrl("http://bloximages.newyork1.vip.townnews.com/journalnow.com/content/tncms/assets/v3/editorial/5/2d/52d54e0c-2786-11e2-abe2-001a4bcf6878/5098201883794.preview-620.jpg");
*/
        return rootview;
    }
}
