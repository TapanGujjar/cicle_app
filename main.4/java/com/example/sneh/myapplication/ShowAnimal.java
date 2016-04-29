package com.example.sneh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ShowAnimal extends AppCompatActivity {
    TextView type,quantity,price,amount;
    int building_id,cicle_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_animal);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting intent
        Intent intent=getIntent();
        final int animal_id=intent.getIntExtra("animal_id",-1);
        Log.d("show_animal_id", String.valueOf(animal_id));

        //settting db_handler and gettting animal object from animal)id
        db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        final animal_class animal=handler.getAnimal(animal_id);
        Log.d("show_animal_type",animal.getType());

        Log.d("Quantity",String.valueOf(animal.getQuantity()));
        //setting up textview
        type=(TextView)findViewById(R.id.show_animal_type);
        quantity=(TextView)findViewById(R.id.show_animal_quantity);
        price=(TextView)findViewById(R.id.show_animal_price);
        amount=(TextView)findViewById(R.id.show_animal_amount);
        type.setText(animal.getType());
        quantity.setText(String.valueOf(animal.getQuantity()));
        price.setText(String.valueOf(animal.getPrice()));
        amount.setText(String.valueOf((animal.getPrice())*animal.getQuantity()));

        building_id=animal.getBuilding_id();
        cicle_id=animal.getCicle_id();
        Log.d("show_animal_cicle_id",String.valueOf(animal.getCicle_id()));
        Log.d("show_animal_building_id",String.valueOf(animal.getBuilding_id()));
        Log.d("show_animal_db_count",String.valueOf(handler.getAnimalCount(cicle_id,building_id)));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.show_animal_editbutton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Animals.class);
                intent.putExtra("cicle_id",animal.getCicle_id());
                intent.putExtra("building_id", animal.getBuilding_id());
                intent.putExtra("animal_id",animal.getAnimal_id());
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent =new Intent(getApplicationContext(),TitleBuilding.class);
        intent.putExtra("building_id",building_id);
        intent.putExtra("cicle_id",cicle_id);
        finish();
        startActivity(intent);

        return true;
    }

}

