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

public class ShowDeath extends AppCompatActivity {

    TextView date,no;
    death_ death;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_death);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting db
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        //getting id;
        Intent intent=getIntent();
        final int death_id=intent.getIntExtra("id", -1);
        Log.d("death_id", String.valueOf(death_id));


        death=handler.getDeath(death_id);

        date=(TextView)findViewById(R.id.show_death_date);
        no=(TextView)findViewById(R.id.show_death_no);

        date.setText(death.getDate());
        no.setText(String.valueOf(death.getDeath_no()));

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_death_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddDeath.class);
                intent.putExtra("cicle_id", death.getCicle_id());
                intent.putExtra("building_id", death.getBuilding_id());
                intent.putExtra("death_id", death.getDeath_id());
                finish();
                startActivity(intent);
            }
        });

        FloatingActionButton delete=(FloatingActionButton)findViewById(R.id.show_death_del);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.deleteDeath(death);
                Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Death.class);
                intent.putExtra("cicle_id",death.getCicle_id());
                intent.putExtra("building_id",death.getBuilding_id());
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ShowDeath.this, Death.class);
        intent.putExtra("cicle_id", death.getCicle_id());
        intent.putExtra("building_id",death.getBuilding_id());
        finish();
        startActivity(intent);
        return true;
    }
}
