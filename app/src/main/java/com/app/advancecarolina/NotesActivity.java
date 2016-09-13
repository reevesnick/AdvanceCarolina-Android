package com.app.advancecarolina;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;

/**
 * Created by neegbeahreeves on 8/29/16.
 */
public class NotesActivity extends AppCompatActivity implements OnClickListener{

    private EditText notesEditText;
    private Button saveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


        notesEditText = (EditText)findViewById(R.id.notesEditText);
        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        loadSavedPreferences();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().show();

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#000000\">" + getString(R.string.notes_string_title) + "</font>")));


    }

    private void loadSavedPreferences() {

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
//        boolean checkBoxValue = sharedPreferences.getBoolean("CheckBox_Value", false);
        String notes = sharedPreferences.getString("storedName", "");
        /*if (checkBoxValue) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }*/
        notesEditText.setText(notes);

    }

    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }



    @Override
    public void onClick(View v){
        savePreferences("storedName", notesEditText.getText().toString());
        finish();

    }

}
