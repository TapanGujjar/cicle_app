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
import android.widget.Toast;

public class ShowWorker extends AppCompatActivity {
    int worker_id;
    db_handler handler;
    worker_class worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_worker);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting up textview
        TextView name=(TextView)findViewById(R.id.show_worker_name);
        TextView address=(TextView)findViewById(R.id.show_worker_address);
        TextView tel=(TextView)findViewById(R.id.show_worker_phone);
        TextView date=(TextView)findViewById(R.id.show_worker_date);
        TextView price=(TextView)findViewById(R.id.show_worker_price);


        //setting up intent to get worker id
        Intent intent=getIntent();
        worker_id=intent.getIntExtra("id",-1);
        Log.d("show_worker_worker_id",String.valueOf(worker_id));

        //setting up db_handler
        handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

       final  worker_class worker1=handler.getWorker(worker_id);
        Log.d("show_worker_title",worker1.getName());
        worker=worker1;
        name.setText(worker1.getName());
        address.setText(worker1.getAddress());
        tel.setText(worker1.getTel());
        date.setText(worker1.getDate_start());
        price.setText(String.valueOf(worker1.getPrice_per_day()));


        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_worker_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowWorker.this, AddWorker.class);
                i.putExtra("cicle_id",worker1.getCicle_id());
                i.putExtra("building_id",worker1.getBuilding_id());
                i.putExtra("worker_id",worker1.getWorker_id());
                startActivity(i);
            }
        });
        FloatingActionButton del = (FloatingActionButton) findViewById(R.id.show_worker_del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int affected_row=handler.deleteWorker(worker);
                String message;
                if(affected_row==1){
                    message="Successfully deleted";
                }
                else{
                    message="Error in deleting";
                }
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Worker.class);
                intent.putExtra("cicle_id",worker1.getCicle_id());
                intent.putExtra("building_id",worker1.getBuilding_id());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(getApplicationContext(),Worker.class);
        intent.putExtra("cicle_id", worker.getCicle_id());
        intent.putExtra("building_id", worker.getBuilding_id());
        startActivity(intent);
        return true;
    }
}
