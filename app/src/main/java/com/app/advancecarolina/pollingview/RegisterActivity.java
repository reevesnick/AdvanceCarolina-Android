package com.app.advancecarolina.pollingview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.advancecarolina.MainActivity;

import com.app.advancecarolina.R;
import com.parse.ParseObject;

/**
 * Created by neegbeahreeves on 9/1/16.
 */
public class RegisterActivity extends AppCompatActivity {
    private EditText fname;
    private EditText lname;
    private EditText address;
    private EditText city;
    private EditText county;
    private EditText state;
    private EditText zip;
    private EditText party;
    private Button alreadyRegistered;
    private Button submitButton;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = (EditText) findViewById(R.id.firstNameText);
        lname = (EditText)findViewById(R.id.lastNameText);
        address = (EditText)findViewById(R.id.addressText);
        city = (EditText)findViewById(R.id.cityText);
        county = (EditText)findViewById(R.id.countyText);
        state = (EditText)findViewById(R.id.stateText);
        //zip = (EditText)findViewById(R.id.zipText);
        party = (EditText)findViewById(R.id.partyText);

        submitButton = (Button) findViewById(R.id.submitButton);
        alreadyRegistered = (Button)findViewById(R.id.alreadyRegistered);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNameString = fname.getText().toString();
                String lastnameString = lname.getText().toString();
                String addressString = address.getText().toString();
                String cityString = city.getText().toString();
                String countyString = county.getText().toString();
                String stateString = state.getText().toString();
                //String zipString = zip.getText().toString(); // No Zip
                String partyString = party.getText().toString();

                if (firstNameString.isEmpty() && lastnameString.isEmpty()&& addressString.isEmpty()
                        && cityString.isEmpty() && stateString.isEmpty() && partyString.isEmpty()){
                    {
                        Snackbar.make(v, "Please fill out the missing information.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }


                ParseObject dataObject = new ParseObject("VoterInformation");
                dataObject.put("first_name",firstNameString);
                dataObject.put("last_name",lastnameString);
                dataObject.put("address",addressString);
                dataObject.put("city",cityString);
                dataObject.put("county",countyString);
                dataObject.put("state",stateString);
                dataObject.put("party",partyString);
                dataObject.saveInBackground();
/*
                Toast.makeText(RegisterActivity.this, "Scenario created",
                        Toast.LENGTH_SHORT).show();
*/
                Intent returnActivity = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(returnActivity);

                Snackbar.make(v, "Thank you for providing you voter information to Advance Carolina.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

        alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnActivity = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(returnActivity);

                Snackbar.make(v, "Thank you for providing you voter information to Advance Carolina.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().show();


    }





}
