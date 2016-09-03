package com.app.advancecarolina;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by neegbeahreeves on 8/26/16.
 */
public class NewsSingleItemView extends AppCompatActivity {
    String headlineLabel;
    String dateLabel;
    String articleLabel;
    String headlineImageLabel;
    ImageView image;
    ProgressDialog pDialog;
    Bitmap bitmap;


    //ImageLoader imageLoader = new ImageLoader(this);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(
                R.layout.newssingleitemview);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().show();

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        Intent i = getIntent();
        headlineLabel = i.getStringExtra("headlineText");

        dateLabel = i.getStringExtra("dateText");

        articleLabel = i.getStringExtra("articleText");

        headlineImageLabel = i.getStringExtra("headlineImage");

        // Locate the TextViews in singleitemview.xml
        TextView txtdate = (TextView) findViewById(R.id.dateText);
        TextView txttitle = (TextView) findViewById(R.id.headlineText);
        TextView txtbody = (TextView) findViewById(R.id.articleText);

        // Locate the ImageView in singleitemview.xml
        image = (ImageView) findViewById(R.id.headlineImage);

        // Set results to the TextViews
        txtdate.setText(dateLabel);
        txttitle.setText(headlineLabel);
        txtbody.setText(articleLabel);
        new LoadImage().execute(headlineImageLabel);

        //img.setImageBitmap(headlineImageLabel);

        //Linkify

        Linkify.addLinks(txtbody, Linkify.ALL);
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
       // imageLoader.DisplayImage(headlineImageLabel, img);
    }

    //Load image file from URL
    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(NewsSingleItemView.this);
            pDialog.setMessage("Loading Article...");
            pDialog.show();

        }
        protected Bitmap doInBackground(String... args) {

            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap aimage) {

            if(image != null){
                image.setImageBitmap(aimage);
                pDialog.dismiss();

            }else{

                pDialog.dismiss();
                Toast.makeText(NewsSingleItemView.this, "Unable to load image", Toast
                        .LENGTH_SHORT).show();

            }
        }
    }


}
