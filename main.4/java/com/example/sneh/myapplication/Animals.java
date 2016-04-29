package com.example.sneh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Animals extends AppCompatActivity {
    int animal_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);

        //setting the intent and getting building_id and cicle_id
        Intent intent=getIntent();
        final int building_id=intent.getIntExtra("building_id", -1);
        final int cicle_id=intent.getIntExtra("cicle_id",-1);
        animal_id=intent.getIntExtra("animal_id",-1);
        Log.d("add_animal_building_id",String.valueOf(building_id));
        Log.d("add_animal_cicle_id",String.valueOf(cicle_id));
        Log.d("add_animal_animal_id",String.valueOf(animal_id));

        //setting up database
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        Log.d("add_animal_animal_count", String.valueOf(handler.getAnimalCount(cicle_id, building_id)));
        if(handler.getAnimalCount(cicle_id,building_id)==1 && animal_id==-1){
            Intent intent1=new Intent(getApplicationContext(),ShowAnimal.class);
            intent1.putExtra("animal_id", handler.getAnimalCount(cicle_id, building_id));
            startActivity(intent1);
        }


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText price = (EditText) findViewById(R.id.price_animal);
        final EditText quantity = (EditText) findViewById(R.id.quantity_animal);
        final TextView amount = (TextView) findViewById(R.id.amount_animal);
        final LinearLayout other1=(LinearLayout)findViewById(R.id.llother);

        final EditText other = (EditText) findViewById(R.id.textView111);
        String[] array_spinner = new String[8];
        array_spinner[0] = "Type(Select)";
        array_spinner[1] = "Type 1";
        array_spinner[2] = "Type 2";
        array_spinner[3] = "Type 3";
        array_spinner[4] = "Type 4";
        array_spinner[5] = "Type 5";
        array_spinner[6] = "Type 6";
        array_spinner[7] = "Other";
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_animal);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        if(animal_id!=-1){
            animal_class animal=handler.getAnimal(animal_id);
            price.setText(String.valueOf(animal.getPrice()));
            quantity.setText(String.valueOf(animal.getQuantity()));

            for(int i=0;i<array_spinner.length;i++){
                if(array_spinner[i].equals(animal.getType())){
                    spinner.setSelection(i);
                }
            }
            int total=animal.getPrice()*animal.getQuantity();
            amount.setText(String.valueOf(total));
            other.setText(animal.getOther());
        }
        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                final int _price = Integer.parseInt(price.getText().toString());
                final int _capacity = Integer.parseInt(quantity.getText().toString());
                final int total=_price*_capacity;
                amount.setText(String.valueOf(total));
            }
        });



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = spinner.getSelectedItem().toString();
                if (str.equals("Other")) {
                    other1.setVisibility(View.VISIBLE);
                }
                else{
                    other1.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Button save_animal=(Button)findViewById(R.id.add_animal_save);
        save_animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(animal_id==-1){
                    Log.d("add_animal_inserted","insert");
                    animal_class animal=new animal_class();
                    animal.setBuilding_id(building_id);
                    animal.setCicle_id(cicle_id);
                    animal.setType(spinner.getSelectedItem().toString());
                    animal.setOther(other.getText().toString());
                    animal.setQuantity(Integer.parseInt(quantity.getText().toString()));
                    animal.setPrice(Integer.parseInt(price.getText().toString()));
                    handler.createAnimal(animal);
                    Toast.makeText(getApplicationContext(),String.valueOf(handler.getAnimalCount(cicle_id,animal_id)),Toast.LENGTH_SHORT).show();
                    List<animal_class> animal_list=handler.getAllAnimal(cicle_id,building_id);
                    animal_id=animal_list.get(animal_list.size()-1).getAnimal_id();
                }
                else{
                    Log.d("add_animal_update","update");
                    animal_class animal=new animal_class();
                    animal.setAnimal_id(animal_id);
                    animal.setBuilding_id(building_id);
                    animal.setCicle_id(cicle_id);
                    animal.setType(spinner.getSelectedItem().toString());
                    Log.d("add_animal_other",other.getText().toString());
                    animal.setOther(other.getText().toString());
                    animal.setQuantity(Integer.parseInt(quantity.getText().toString()));
                    animal.setPrice(Integer.parseInt(price.getText().toString()));
                    int update=handler.updateAnimal(animal);
                    String message;
                    if(update==1){
                        message="Successfully updated";
                    }
                    else{
                        message="Error in updating";
                    }
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                }
                Intent intent=new Intent(getApplicationContext(),ShowAnimal.class);
                intent.putExtra("animal_id",animal_id);
                startActivity(intent);
            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}
