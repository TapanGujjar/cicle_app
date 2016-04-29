package com.example.sneh.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import Adapters.EachListAdapter;

public class Worker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_worker);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting intent and gettting cicle_id and building_id
        Intent intent=getIntent();
        final int cicle_id=intent.getIntExtra("cicle_id",-1);
        final int building_id=intent.getIntExtra("building_id",-1);
        Log.d("worker_cicle_id", String.valueOf(cicle_id));
        Log.d("worker_building_id", String.valueOf(building_id));

        //setting up db_handler
        db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());


        List<worker_class> worker_list=handler.get_all_worker(cicle_id,building_id);
        Log.d("Worker_worker_list_size",String.valueOf(worker_list.size()));

        String[] text1 = new String[worker_list.size()];
        String[] text2 = new String[worker_list.size()];
        String[] text3 = new String[worker_list.size()];
        String[] text4 = new String[worker_list.size()];
        String[] text5 = new String[worker_list.size()];
        int[] id=new int[worker_list.size()];

        for(int i=0;i<worker_list.size();i++){
            text1[i]="";
            text2[i]=worker_list.get(i).getName();
            text3[i]=String.valueOf(worker_list.get(i).getPrice_per_day());
            text4[i]="";
            text5[i]="";
            id[i]=worker_list.get(i).getWorker_id();

        }

        ListView lv = (ListView) findViewById(R.id.worker_list);
        lv.setAdapter(new EachListAdapter(Worker.this, 1, text1, text2, text3, text4, text5,id));

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.add_worker);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Worker.this, AddWorker.class);
                intent.putExtra("cicle_id", cicle_id);
                intent.putExtra("building_id", building_id);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
