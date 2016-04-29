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

public class ShowFood extends AppCompatActivity {
    int food_id;
    TextView date,designation,quantity,price,amount;
    food_class food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food);
        //setting db
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        //setting up intent
        Intent intent=getIntent();
        food_id=intent.getIntExtra("id",-1);
        Log.d("show_food_food_id", String.valueOf(food_id));
        food=handler.getFood(food_id);
        //setting textview
        date=(TextView)findViewById(R.id.show_food_date);
        designation=(TextView)findViewById(R.id.show_food_designation);
        price=(TextView)findViewById(R.id.show_food_price);
        quantity=(TextView)findViewById(R.id.show_food_quantity);
        amount=(TextView)findViewById(R.id.show_food_amount);
        date.setText(food.getDate());
        designation.setText(food.getDesignation());
        price.setText(String.valueOf(food.getPrice()));
        quantity.setText(String.valueOf(food.getQuantity()));
        int total=food.getPrice()*food.getQuantity();
        amount.setText(String.valueOf(total));

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_food_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowFood.this, AddFood.class);
                intent.putExtra("cicle_id",food.getCicle_id());
                intent.putExtra("building_id",food.getBuilding_id());
                intent.putExtra("food_id",food_id);
                finish();
                startActivity(intent);
            }
        });
        FloatingActionButton del = (FloatingActionButton) findViewById(R.id.show_food_del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int delete_row=handler.deleteFood(food);

                Intent intent = new Intent(ShowFood.this, Food.class);
                intent.putExtra("cicle_id",food.getCicle_id());
                intent.putExtra("building_id",food.getBuilding_id());
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ShowFood.this, Food.class);
        intent.putExtra("cicle_id",food.getCicle_id());
        intent.putExtra("building_id",food.getBuilding_id());
        finish();
        startActivity(intent);
        return true;
    }
}
