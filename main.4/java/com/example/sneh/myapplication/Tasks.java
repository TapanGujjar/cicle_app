package com.example.sneh.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Adapters.TaskAdapter;

public class Tasks extends AppCompatActivity {
    db_handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TaskAdapter adapter=new TaskAdapter(getSupportFragmentManager(),getApplicationContext());
        viewPager.setAdapter(adapter);
        // Give the TabLayout the ViewPager
        TabLayout tabs = (TabLayout) findViewById(R.id.sliding_tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.setSelectedTabIndicatorColor(Color.parseColor("#fff176"));
        tabs.setBackgroundColor(Color.parseColor("#fdd835"));
        tabs.setTabTextColors(Color.parseColor("#ffffff"),Color.parseColor("#f5f5f5"));

        Button task = (Button) findViewById(R.id.task);
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tasks.this, TodaysTask.class));
            }
        });

    }

}
