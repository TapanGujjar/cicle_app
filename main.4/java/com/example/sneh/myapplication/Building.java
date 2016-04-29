package com.example.sneh.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Adapters.BuildListAdapter;

public class Building extends AppCompatActivity {

    db_handler handler;
    //int building_id;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);
        handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_build);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.add_build);
        FloatingActionButton done_cicle = (FloatingActionButton) findViewById(R.id.building_done_cicle);
        Intent intent=getIntent();

        position=intent.getIntExtra("position", -1);

        Log.d("building_cicle_id", String.valueOf(position));

        final cicle cicle=handler.getCicle(position);

        done_cicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cicle.setDone(2);
                int count=handler.updateCicle(cicle);
                String message;
                Toast.makeText(getApplicationContext(),"building_cicle_update_info"+String.valueOf(count),Toast.LENGTH_SHORT).show();
                if(count==cicle.getCicle_id()){
                    message="successfully completed";
                    Toast.makeText(getApplicationContext(),"Successfully completed",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),Tasks.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Successfully completed",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),Tasks.class);
                    startActivity(intent);
                }

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Building.this, AddBuilding.class);
                i.putExtra("cicle_id",position);
                startActivity(i);
            }
        });

        List<building_class> building_list=handler.get_all_building(position);
        ListView lv = (ListView) findViewById(R.id.build_list);
        lv.setAdapter(new BuildListAdapter(Building.this , building_list));

        TextView number = (TextView) findViewById(R.id.textView13);
        number.setText("Buildings (" + building_list.size() + ")");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                //finish();
                Intent i = new Intent(Building.this, ShowCicle.class);
                i.putExtra("cicle_id", position);
                startActivity(i);

                return true;

            default:
                finish();
                return true;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_title_building, menu);
        return true;
    }

}
