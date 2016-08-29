package com.app.advancecarolina.pollingview;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.app.advancecarolina.R;

/**
 * Created by neegbeahreeves on 8/23/16.
 */
public class ScheduleVoteActivity extends AppCompatActivity {

    private String eventTitle;

    private Button scheduleButton;
    private EditText eventTitleText;


    public void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_create_schedule);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().show();

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        eventTitleText = (EditText)findViewById(R.id.titleeditText);
        scheduleButton = (Button)findViewById(R.id.create);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

}
