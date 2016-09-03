package com.app.advancecarolina;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.advancecarolina.Services.PlainCircleImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by neegbeahreeves on 8/30/16.
 */
public class CandidateSingleItemView extends AppCompatActivity{
    String candidateName;
    String candidateParty;
    String candidateBio;
    String candidateWebsite;
    String candidatePic;
    ImageView image;
    ProgressDialog pDialog;
    Bitmap bitmap;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candidatesingleitemview);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().show();

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent i =getIntent();
        candidateName = i.getStringExtra("candidateName");
        candidateParty = i.getStringExtra("candidateParty");
        candidateBio = i.getStringExtra("candidateInfo");
        candidateWebsite = i.getStringExtra("candidateURL");
        candidatePic = i.getStringExtra("candidatePicture");

        TextView txtname = (TextView)findViewById(R.id.candidateText);
        TextView txtparty = (TextView)findViewById(R.id.partyText);
        TextView txtbio = (TextView)findViewById(R.id.descriptionText);
        TextView txtweb = (TextView)findViewById(R.id.websiteText);
        image = (PlainCircleImageView) findViewById(R.id.CandidatePic);

        txtname.setText(candidateName);
        txtparty.setText(candidateParty);
        txtbio.setText(candidateBio);
        txtweb.setText(candidateWebsite);
        new LoadImage().execute(candidatePic);

    }

    //Load image file from URL
    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CandidateSingleItemView.this);
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
                Toast.makeText(CandidateSingleItemView.this, "Unable to load image", Toast
                        .LENGTH_SHORT).show();

            }
        }
    }


}
