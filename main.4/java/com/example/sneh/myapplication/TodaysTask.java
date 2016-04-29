package com.example.sneh.myapplication;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import Adapters.TaskAdapter;
import Adapters.TodaysTaskAdapter;

public class TodaysTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_task);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TodaysTaskAdapter adapter = new TodaysTaskAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabs = (TabLayout) findViewById(R.id.sliding_tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.setSelectedTabIndicatorColor(Color.parseColor("#fff176"));
        tabs.setBackgroundColor(Color.parseColor("#fdd835"));
        tabs.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#f5f5f5"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}
