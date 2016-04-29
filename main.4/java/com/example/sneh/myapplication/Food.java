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
import android.widget.TextView;

import java.util.List;

import Adapters.EachListAdapter;

public class Food extends AppCompatActivity {
    int cicle_id;
    int building_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_food);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //setting up intent
        Intent intent=getIntent();
         cicle_id=intent.getIntExtra("cicle_id", -1);
         building_id=intent.getIntExtra("building_id",-1);
        final int food_id=intent.getIntExtra("food_id",-1);
        Log.d("In_equipment_cicle_id", String.valueOf(cicle_id));
        Log.d("In_equip_building_id", String.valueOf(building_id));
        Log.d("In equip_food_id", String.valueOf(food_id));

        //setting up db_handler to get cicle-id,building_id and food_id
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        List<food_class> food_list=handler.getAllFood(cicle_id,building_id);
        Log.d("food_list_size",String.valueOf(food_list.size()));

        String[] text1 =new String[food_list.size()];
        String[] text2 =new String[food_list.size()];
        String[] text3 =new String[food_list.size()];
        String[] text4 =new String[food_list.size()];
        String[] text5 =new String[food_list.size()];
        int[] id=new int[food_list.size()];

        for(int i=0;i<food_list.size();i++){
            text1[i]="DESIGNATION";
            text2[i]=food_list.get(i).getDesignation();
            text3[i]=food_list.get(i).getDate();
            text4[i]="Amount";
            int amount=food_list.get(i).getPrice()*food_list.get(i).getQuantity();
            text5[i]=String.valueOf(amount);
            id[i]=food_list.get(i).getFood_id();
        }

        ListView lv = (ListView) findViewById(R.id.food_list);
        lv.setAdapter(new EachListAdapter(Food.this, 2, text1, text2, text3, text4, text5,id));

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.add_food);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Food.this, AddFood.class);
                intent.putExtra("cicle_id", cicle_id);
                intent.putExtra("building_id", building_id);
                finish();
                startActivity(intent);
            }
        });
        TextView number = (TextView) findViewById(R.id.textView13);
        number.setText("Eggs (" + food_list.size() + ")");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(getApplicationContext(),TitleBuilding.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        finish();
        startActivity(intent);

        return true;
    }
}
