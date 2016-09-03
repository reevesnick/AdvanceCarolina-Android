package com.app.advancecarolina;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import com.app.advancecarolina.pollingview.PollLocationActivity;
import com.app.advancecarolina.pollingview.RegisterActivity;
import com.app.advancecarolina.pollingview.ScheduleVoteActivity;
import com.batch.android.Batch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab_main, fab_poll,fab_schedule, fab_notes;
    Animation FabOpen, FabClose, FabClockwise, Fabanticlockwise;
    boolean isOpen = false;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        fab_main = (FloatingActionButton)findViewById(R.id.mainfab);
        fab_poll = (FloatingActionButton)findViewById(R.id.fab);
        //fab_schedule = (FloatingActionButton)findViewById(R.id.fabSchedule);
        fab_notes = (FloatingActionButton)findViewById(R.id.fabNotes);


        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        Fabanticlockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);

        sharedPreferences = getSharedPreferences("ShaPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        boolean  firstTime=sharedPreferences.getBoolean("first", true);
        if(firstTime) {
            editor.putBoolean("first",false);
            //For commit the changes, Use either editor.commit(); or  editor.apply();.
            editor.commit();
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen){
                    fab_poll.startAnimation(FabClose);
                   // fab_schedule.startAnimation(FabClose);
                    fab_notes.startAnimation(FabClose);
                    fab_main.startAnimation(Fabanticlockwise);

                    fab_poll.setClickable(false);
                    //fab_schedule.setClickable(false);
                    fab_notes.setClickable(false);
                    isOpen = false;
                }
                else{
                   fab_poll.startAnimation(FabOpen);
                    //fab_schedule.startAnimation(FabOpen);
                    fab_notes.startAnimation(FabOpen);
                    fab_main.startAnimation(FabClockwise);

                    fab_poll.setClickable(true);
                    //fab_schedule.setClickable(true);
                    fab_notes.setClickable(true);
                    isOpen = true;
                }
            }
        });

        fab_poll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this,PollLocationActivity.class));

            }
        });
/*
        fab_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ScheduleVoteActivity.class));

            }
        });
*/
        fab_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NotesActivity.class));
                Snackbar.make(view, "Note Saved", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NewsFragment(), "News");
        adapter.addFragment(new BallotFragment(), "Ballot");
        //adapter.addFragment(new PollingView(), "Poll");
        adapter.addFragment(new CandidatesFragment(), "Candidates");
        //adapter.addFragment(new ContactUsActivity(), "Contact Us");
        viewPager.setAdapter(adapter);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if (id == R.id.contact_us){
            Intent contact = new Intent(this, ContactUsActivity.class);
            startActivity(contact);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Batch.onStart(this);
    }

    @Override
    protected void onStop()
    {
        Batch.onStop(this);

        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        Batch.onDestroy(this);

        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        Batch.onNewIntent(this, intent);

        super.onNewIntent(intent);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
