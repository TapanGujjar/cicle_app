package com.example.sneh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEquipment extends AppCompatActivity {

    EditText type,designation,capacity,price;
    Button save;
    int cicle_id,building_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equipment);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_equipment);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        save = (Button) findViewById(R.id.add_equipment_save);
        type=(EditText)findViewById(R.id.add_equipment_type);
        designation=(EditText)findViewById(R.id.add_equipment_designation);
        capacity=(EditText)findViewById(R.id.add_equipment_quantity);
        price=(EditText)findViewById(R.id.add_equipment_price);
        //Setting db_handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        /*getting building_id and cicle_id*/
        Intent intent=getIntent();
        cicle_id=intent.getIntExtra("cicle_id", -1);
        building_id=intent.getIntExtra("building_id",-1);
        final int equip_id=intent.getIntExtra("equip_id",-1);

        if(equip_id!=-1){
            equipment equipment=handler.getEquipment(equip_id);
            type.setText(equipment.getType());
            designation.setText(equipment.getDesignation());
            capacity.setText(String.valueOf(equipment.getQuantity()));
            price.setText(String.valueOf(equipment.getPrice()));
        }
        Log.d("Add_equipment_equip_id",String.valueOf(equip_id));
        Log.d("Add_equipment_cicle_id", String.valueOf(cicle_id));
        Log.d("Add_equip_building_id", String.valueOf(building_id));



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(equip_id!=-1){
                    equipment equipment=new equipment();
                    equipment.setEquip_id(equip_id);
                    equipment.setCicle_id(cicle_id);
                    equipment.setBuilding_id(building_id);
                    equipment.setType(type.getText().toString());
                    equipment.setDesignation(designation.getText().toString());
                    equipment.setQuantity(Integer.parseInt(capacity.getText().toString()));
                    equipment.setPrice(Integer.parseInt(price.getText().toString()));
                    handler.updateEquipment(equipment);
                    Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),Equipment.class);
                    intent.putExtra("cicle_id", cicle_id);
                    intent.putExtra("building_id", building_id);
                    finish();
                    startActivity(intent);
                }
                else {
                    equipment equipment=new equipment();
                    equipment.setCicle_id(cicle_id);
                    equipment.setBuilding_id(building_id);
                    equipment.setType(type.getText().toString());
                    equipment.setDesignation(designation.getText().toString());
                    equipment.setQuantity(Integer.parseInt(capacity.getText().toString()));
                    equipment.setPrice(Integer.parseInt(price.getText().toString()));
                    handler.createEquipment(equipment);
                    Toast.makeText(getApplicationContext(),"Successfully created",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Equipment.class);
                    intent.putExtra("cicle_id", cicle_id);
                    intent.putExtra("building_id", building_id);
                    finish();
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), Equipment.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
        //startActivity(new Intent(getApplicationContext(), Equipment.class));
        return true;
    }
}
