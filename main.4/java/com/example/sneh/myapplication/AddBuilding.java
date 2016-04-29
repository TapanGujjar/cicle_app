package com.example.sneh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddBuilding extends AppCompatActivity {
    EditText title,capacity;
    Button add_building_save;
    db_handler handler;
    private String[] array_spinner;
    int [] images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_building);

        title=(EditText)findViewById(R.id.add_building_title);
        capacity=(EditText)findViewById(R.id.add_building_capacity);
        final Spinner spinner = (Spinner) findViewById(R.id.add_building_type);
        final EditText other=(EditText)findViewById(R.id.show_building_other);
        add_building_save=(Button)findViewById(R.id.add_building_save);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_build);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        array_spinner = new String[5];
        array_spinner[0] = "Type(Select)";
        array_spinner[1] = "type1";
        array_spinner[2] = "Type 2";
        array_spinner[3] = "Type 3";
        array_spinner[4]="Other";


        images= new int[]{0, R.drawable.simplebuilding1, R.drawable.simplebuilding2, R.drawable.simplebuilding3,R.drawable.other};

        //  spinner = (Spinner) findViewById(R.id.add_cicle_type);
        // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        // spinner.setAdapter(adapter);
        spinner.setAdapter(new MyAdapter(AddBuilding.this,R.layout.row,array_spinner));
        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = spinner.getSelectedItem().toString();
                if(str.equals("Other")){
                    other.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Intent intent=getIntent();
        final int cicle_id=intent.getIntExtra("cicle_id",-1);
        Log.d("add_cicle_cicle_id",String.valueOf(cicle_id));
        handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        add_building_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _title=title.getText().toString();
                int _capacity=Integer.parseInt(capacity.getText().toString().trim());
                String _spinner=spinner.getSelectedItem().toString();
                String _other=other.getText().toString();
                building_class building=new building_class();
                building.setCicle_id(cicle_id);
                building.setTitle(_title);
                building.setCapacity(_capacity);
                building.setType(_spinner);
                building.setOther(_other);
                handler.createBuilding(building);
                Toast.makeText(getApplicationContext(),"Successfully created building",Toast.LENGTH_SHORT);
                Intent intent=new Intent(getApplicationContext(),Building.class);
                intent.putExtra("position",cicle_id);
                startActivity(intent);
            }
        });

    }
    public class MyAdapter extends ArrayAdapter<String>{

        public MyAdapter(Context context, int textViewResourceId,  String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.row, parent, false);
            TextView label=(TextView)row.findViewById(R.id.company);

            label.setText(array_spinner[position]);


            ImageView icon=(ImageView)row.findViewById(R.id.image);
            if(position!=0) {
                icon.setImageResource(images[position]);
            }
            return row;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}