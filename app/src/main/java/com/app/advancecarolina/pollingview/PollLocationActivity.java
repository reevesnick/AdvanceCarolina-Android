package com.app.advancecarolina.pollingview;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.app.advancecarolina.R;
import com.app.advancecarolina.Services.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by neegbeahreeves on 8/23/16.
 */
public class PollLocationActivity extends AppCompatActivity {

    private Button registerButton;
    private Button searchButton;
    private SearchView search;

    Context context = getBaseContext();
    int duration = Toast.LENGTH_SHORT;


    private ProgressDialog pDialog;

    private static String addressInput;
    private String url; // = "https://www.googleapis.com/civicinfo/v2/voterinfo?address="+addressInput+"&key=AIzaSyBv_gphp6mNE2JbSmiPlu-QUNJyuviKMQg&electionId=2000";
    private TextView emptyString;

    // JSON Node names
    private static final String TAG_POLLINGLOCATIONS= "pollingLocations";
    private static final String TAG_LOCATION = "locationName";
    private static final String TAG_LINE = "line1";
    private static final String TAG_CITY = "city";
    private static final String TAG_STATE = "state";
    private static final String TAG_ZIP = "zip";

    //poll JSONArray
    JSONArray polls = null;

    //Hashmao for ListView
    ArrayList<HashMap<String, String>> pollList;


    @Override
    public void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_poll_location);

        pollList = new ArrayList<HashMap<String, String>>();

        searchButton = (Button)findViewById(R.id.searchButton);
        final EditText edt = (EditText)findViewById(R.id.searchTextField);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressInput = edt.getText().toString();
                addressInput = addressInput.replaceAll(" ","%20");

                url = "https://www.googleapis.com/civicinfo/v2/voterinfo?address="+addressInput+"&key=AIzaSyBv_gphp6mNE2JbSmiPlu-QUNJyuviKMQg&electionId=2000";
                new GetPolls().execute();

            }
        });



        ListView lv =(ListView) findViewById(R.id.listView2);

        lv.setEmptyView(findViewById(android.R.id.empty));


        // Calling async task to get json
       // new GetPolls().execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().show();

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#000000\">" + getString(R.string.poll_string_title) + "</font>")));

        registerButton = (Button)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://register.rockthevote.com/registrants/new?partner=34021&source=embed-rtv468x60v1";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_search, menu);

        // Get the SearchView and set the searchable configuration
        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        // Assumes current activity is the searchable activity
        //      searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        //    searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }


    private class GetPolls extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(PollLocationActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            //Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null){
                try{
                    JSONObject jsonObj = new JSONObject(jsonStr);



                    polls = jsonObj.getJSONArray(TAG_POLLINGLOCATIONS);

                    for (int i = 0; i< polls.length(); i++){
                        JSONObject c = polls.getJSONObject(i);

                        JSONObject addressNode = c.getJSONObject("address");


                        String locationName = addressNode.getString(TAG_LOCATION);
                        String address = addressNode.getString(TAG_LINE);
                        String city = addressNode.getString(TAG_CITY);
                        String state = addressNode.getString(TAG_STATE);
                        String zipCode = addressNode.getString(TAG_ZIP);

                        HashMap<String, String> poll = new HashMap<String, String>();

                        poll.put(TAG_LOCATION, locationName);
                        poll.put(TAG_LINE, address);
                        poll.put(TAG_CITY, city);
                        poll.put(TAG_STATE, state);
                        poll.put(TAG_ZIP,zipCode);

                        pollList.add(poll);

                    }
                }
                catch (JSONException e){
                    e.printStackTrace();


                }
            }
            else{
                Log.e("ServiceHandler", "Couldn't get any data from the url");

            }


            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (result == null){
                Toast.makeText(getApplicationContext(), "Unable to obtain data. Check the input and try again. ", duration).show();

            }

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    PollLocationActivity.this, pollList,
                    R.layout.activity_poll_location_item, new String[] { TAG_LOCATION, TAG_LINE,
                    TAG_CITY, TAG_STATE, TAG_ZIP }, new int[] { R.id.locationName,
                    R.id.addressText, R.id.cityText, R.id.stateText, R.id.zipText });


            ListView lv =(ListView) findViewById(R.id.listView2);

            emptyString = (TextView)findViewById(R.id.empty);
            lv.setEmptyView(emptyString);


            lv.setAdapter(adapter);

        }

    }
}
