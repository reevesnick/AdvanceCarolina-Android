package com.app.advancecarolina;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by neegbeahreeves on 8/26/16.
 */
public class NewsSingleItemView extends Activity {
    String headlineLabel;
    String dateLabel;
    String articleLabel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(
                R.layout.newssingleitemview);
/*
        // Look up the AdView as a resource and load a request.
        AdView adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
*/

        Intent i = getIntent();
        // Get the result of rank
        dateLabel = i.getStringExtra("dateText");
        // Get the result of country
        headlineLabel = i.getStringExtra("headlineText");
        // Get the result of population
        articleLabel = i.getStringExtra("articleText");
        // Get the result of flag
       // imageLabel = i.getStringExtra("imageFile");

        // Locate the TextViews in singleitemview.xml
        TextView txtdate = (TextView) findViewById(R.id.dateText);
        TextView txttitle = (TextView) findViewById(R.id.headlineText);
        TextView txtbody = (TextView) findViewById(R.id.articleText);

        // Locate the ImageView in singleitemview.xml
        //ImageView img = (ImageView) findViewById(R.id.image);

        // Set results to the TextViews
        txtdate.setText(dateLabel);
        txttitle.setText(headlineLabel);
        txtbody.setText(articleLabel);

        //Linkify

        //Linkify.addLinks(txtbody, Linkify.ALL);
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        //imageLoader.DisplayImage(imageLabel, img);
    }
}
