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

public class ShowEgg extends AppCompatActivity {

    TextView date,number,type;
    egg_ egg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_egg);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting db
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        //getting id;
        Intent intent=getIntent();
        final int egg_id=intent.getIntExtra("id", -1);
        Log.d("egg_id", String.valueOf(egg_id));


        egg=handler.getEgg(egg_id);

        date=(TextView)findViewById(R.id.show_egg_date);
        number=(TextView)findViewById(R.id.show_egg_number);
        type=(TextView)findViewById(R.id.show_egg_type);

        date.setText(egg.getDate());
        number.setText(String.valueOf(egg.getNumber()));
        type.setText(egg.getType());

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_egg_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddEgg.class);
                intent.putExtra("cicle_id", egg.getCicle_id());
                intent.putExtra("building_id", egg.getBuilding_id());
                intent.putExtra("egg_id", egg.getEgg_id());
                finish();
                startActivity(intent);
            }
        });

        FloatingActionButton delete=(FloatingActionButton)findViewById(R.id.show_egg_del);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.deleteEgg(egg);
                Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Egg.class);
                intent.putExtra("cicle_id",egg.getCicle_id());
                intent.putExtra("building_id", egg.getBuilding_id());
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ShowEgg.this, Egg.class);
        intent.putExtra("cicle_id", egg.getCicle_id());
        intent.putExtra("building_id",egg.getBuilding_id());
        finish();
        startActivity(intent);
        return true;
    }
}
