package com.app.advancecarolina.pollingview;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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

    private ProgressDialog pDialog;

    private static String addressInput = "704%20E%20Lindsay%20St";
    private static String url = "https://www.googleapis.com/civicinfo/v2/voterinfo?address="+addressInput+",%20NC&key=AIzaSyBv_gphp6mNE2JbSmiPlu-QUNJyuviKMQg&electionId=2000";

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
        /*
        SearchView searchText = (SearchView) findViewById(R.id.searchView);
        searchText.setQuery(addressInput, false);
*/

        ListView lv =(ListView) findViewById(R.id.listView2);

        // Calling async task to get json
        new GetPolls().execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().show();

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

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


            lv.setAdapter(adapter);
        }

    }
}
