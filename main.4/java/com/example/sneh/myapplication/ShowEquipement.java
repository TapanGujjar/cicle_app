package com.example.sneh.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ShowEquipement extends AppCompatActivity {
    TextView type,designation,quantity,price;
    equipment equipment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_equipement);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //setting db
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        //getting id;
        Intent intent=getIntent();
        final int equip_id=intent.getIntExtra("id", -1);
        Log.d("equip_id",String.valueOf(equip_id));


        equipment=handler.getEquipment(equip_id);

        type=(TextView)findViewById(R.id.show_equipment_type);
        designation=(TextView)findViewById(R.id.show_equipment_designation);
        price=(TextView)findViewById(R.id.show_equipment_price);
        quantity=(TextView)findViewById(R.id.show_equipment_quantity);

        type.setText(equipment.getType());
        designation.setText(equipment.getDesignation());
        quantity.setText(String.valueOf(equipment.getQuantity()));
        price.setText(String.valueOf(equipment.getPrice()));

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_equi_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddEquipment.class);
                intent.putExtra("cicle_id", equipment.getCicle_id());
                intent.putExtra("building_id", equipment.getBuilding_id());
                intent.putExtra("equip_id", equipment.getEquip_id());
                finish();
                startActivity(intent);
            }
        });

        FloatingActionButton delete=(FloatingActionButton)findViewById(R.id.show_equi_del);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.deleteEquipment(equipment);
                Toast.makeText(getApplicationContext(),"Successfully Deleted",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Equipment.class);
                intent.putExtra("cicle_id",equipment.getCicle_id());
                intent.putExtra("building_id",equipment.getBuilding_id());
                finish();
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ShowEquipement.this, Equipment.class);
        intent.putExtra("cicle_id",equipment.getCicle_id());
        intent.putExtra("building_id",equipment.getBuilding_id());
        finish();
        startActivity(intent);
        return true;
     }
}
