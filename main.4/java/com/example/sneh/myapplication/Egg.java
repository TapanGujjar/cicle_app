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
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import Adapters.EachListAdapter;

public class Egg extends AppCompatActivity {

    int cicle_id, building_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_egg);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*getting building_id and cicle_id*/
        Intent intent=getIntent();
        cicle_id=intent.getIntExtra("cicle_id", -1);
        building_id=intent.getIntExtra("building_id",-1);
        Log.d("In_equipment_cicle_id", String.valueOf(cicle_id));
        Log.d("In_equip_building_id", String.valueOf(building_id));

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.add_egg);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Egg.this, AddEgg.class);
                intent.putExtra("cicle_id", cicle_id);
                intent.putExtra("building_id", building_id);
                finish();
                startActivity(intent);

            }
        });

        //Setting db_handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        List<egg_> egg_list=handler.get_all_egg(cicle_id, building_id);
        Log.d("egg_list_size",String.valueOf(egg_list.size()));
        String[] text1 =new String[egg_list.size()];
        String[] text2 =new String[egg_list.size()];
        String[] text6 = new String[egg_list.size()];
        String[]text4 =new String[egg_list.size()];
        String[] text5 =new String[egg_list.size()];
        int[] egg_id=new int[egg_list.size()];
        for(int i=0;i<egg_list.size();i++){
            text1[i]="Eggs";
            text2[i]=String.valueOf(egg_list.get(i).getNumber());
            text6[i]=egg_list.get(i).getDate();
            text4[i]="Type";
            text5[i]=egg_list.get(i).getType();
            egg_id[i]=egg_list.get(i).getEgg_id();
        }
        ListView lv = (ListView) findViewById(R.id.egg_list);
        lv.setAdapter(new EachListAdapter(Egg.this , 3 ,text1,text2,text6,text4,text5,egg_id));

        TextView number = (TextView) findViewById(R.id.textView13);
        number.setText("Eggs (" + egg_list.size() + ")");
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
