package com.example.sneh.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowCicle extends AppCompatActivity {
    TextView title,owner,location,type;
    db_handler handler;
    Button show_cicle_save;
    int cicle_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cicle);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        Intent intent =getIntent();                             //getting intent
        cicle_id=intent.getIntExtra("cicle_id", -1);
        Log.d("show_building_cicle_id",String.valueOf(cicle_id));



        final cicle cicle = handler.getCicle(cicle_id);
        String _title=cicle.getTitle();
        String _owner=cicle.getOwner();
        String _location=cicle.getLocation();
        String _type=cicle.getType();
        String _start_date=cicle.getStart_date();
        Log.d("show_cicle_title",_title);

        title=(TextView)findViewById(R.id.show_cicle_title);
        owner=(TextView)findViewById(R.id.show_cicle_owner);
        location=(TextView)findViewById(R.id.show_cicle_location);
        type=(TextView)findViewById(R.id.show_cicle_type);
        title.setText(_title);
        owner.setText(_owner);
        location.setText(_location);
        type.setText(_type);

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_cicle_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCicle.class);
                intent.putExtra("cicle_id", cicle.getCicle_id());
                finish();
                startActivity(intent);
            }
        });

        FloatingActionButton delete=(FloatingActionButton)findViewById(R.id.show_cicle_del);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.deleteCicle(cicle);
                Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Tasks.class);

                finish();
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
