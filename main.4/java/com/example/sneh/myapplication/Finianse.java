package com.example.sneh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import Adapters.FinianseListAdapter;

public class Finianse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finianse);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button expense = (Button) findViewById(R.id.expense);
        Button dp = (Button) findViewById(R.id.dp);
        Button re = (Button) findViewById(R.id.re);

        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Finianse.this, Expense.class));
            }
        });

        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Finianse.this, DownPayment.class));
            }
        });

        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Finianse.this, ReturnedExpenses.class));
            }
        });

        ListView listView=(ListView)findViewById(R.id.finance_listview);
        listView.setAdapter(new FinianseListAdapter(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}
