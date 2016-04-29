package com.example.sneh.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ShowBuilding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_building);

        Intent i = getIntent();
        int building_id = i.getIntExtra("building_id", -1);

        db_handler handler = new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        building_class building_class = handler.getBuilding(building_id);

        Log.d("building_id_show", String.valueOf(building_id));

        TextView title = (TextView) findViewById(R.id.show_building_title);
        TextView capacity = (TextView) findViewById(R.id.show_building_capacity);
        TextView type = (TextView) findViewById(R.id.show_building_type);

        title.setText(building_class.getTitle());
        capacity.setText(String.valueOf(building_class.getCapacity()));
        type.setText(building_class.getType());

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_build_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowBuilding.this, AddBuilding.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
