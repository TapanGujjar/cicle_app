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

public class Death extends AppCompatActivity {

    int cicle_id, building_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_death);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        cicle_id=intent.getIntExtra("cicle_id", -1);
        building_id=intent.getIntExtra("building_id",-1);
        Log.d("In_equipment_cicle_id", String.valueOf(cicle_id));
        Log.d("In_equip_building_id", String.valueOf(building_id));

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.add_death);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Death.this, AddDeath.class);
                intent.putExtra("cicle_id", cicle_id);
                intent.putExtra("building_id", building_id);
                finish();
                startActivity(intent);

            }
        });
        //Setting db_handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        List<death_> death_list=handler.get_all_death(cicle_id, building_id);
        Log.d("death_list_size",String.valueOf(death_list.size()));
        String[] text1 =new String[death_list.size()];
        String[] text2 =new String[death_list.size()];
        String[] text6 = new String[death_list.size()];
        String[]text4 =new String[death_list.size()];
        String[] text5 =new String[death_list.size()];
        int[] death_id=new int[death_list.size()];
        for(int i=0;i<death_list.size();i++){
            text1[i]="Death";
            text2[i]=String.valueOf(death_list.get(i).getDeath_no());
            //int amount=(equipment_list.get(i).getPrice())*(equipment_list.get(i).getQuantity());
            text6[i]=death_list.get(i).getDate();
            //text4[i]="type";
            //text5[i]=equipment_list.get(i).getType();
            death_id[i]=death_list.get(i).getDeath_id();
        }
        ListView lv = (ListView) findViewById(R.id.death_list);
        lv.setAdapter(new EachListAdapter(Death.this , 4 ,text1,text2,text6,text4,text5,death_id));

        TextView number = (TextView) findViewById(R.id.textView13);
        number.setText("Deaths (" + death_list.size() + ")");
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
