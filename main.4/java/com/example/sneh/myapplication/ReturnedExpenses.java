package com.example.sneh.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import Adapters.EachListAdapter;

public class ReturnedExpenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returned_expenses);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_re);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.add_re);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReturnedExpenses.this, AddReturnedExpenses.class);
                startActivity(i);
            }
        });

        String[] text1 = {"Designation 1", "Designation 2", "Designation 3"};
        String[] text2 = {"","",""};
        String[] text3 = {"","",""};
        String[] text4 = {"Type","Type","Type"};
        String[] text5 = {"equipment 1", "equipment 2", "equipment 3"};
        ListView lv = (ListView) findViewById(R.id.re_list);
        //lv.setAdapter(new EachListAdapter(ReturnedExpenses.this, 7, text1, text2, text3, text4, text5));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
