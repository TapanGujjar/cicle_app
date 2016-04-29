package com.example.sneh.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class AddFood extends AppCompatActivity {

    int year_x, month_x, day_x;
    private TextView date_text;
    private Calendar calendar;
    int food_id,cicle_id,building_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        //setting db
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        //setting up intent
        Intent intent=getIntent();
        cicle_id=intent.getIntExtra("cicle_id",-1);
        building_id=intent.getIntExtra("building_id",-1);
        food_id=intent.getIntExtra("food_id",-1);
        Log.d("add_food_cicle_id", String.valueOf(cicle_id));
        Log.d("add_food_building_id", String.valueOf(building_id));
        Log.d("adD_food_food_id", String.valueOf(food_id));

        date_text = (TextView) findViewById(R.id.add_food_start_date);
        calendar=Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year_x, month_x + 1, day_x);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_equipment);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText price = (EditText) findViewById(R.id.price_add_food);
        final EditText quantity = (EditText) findViewById(R.id.quantity_add_food);
        final EditText designation=(EditText)findViewById(R.id.add_food_designation);
        final TextView amount = (TextView) findViewById(R.id.amount_add_food);

        if(food_id!=-1){
            food_class food=handler.getFood(food_id);
            date_text.setText(food.getDate());
            designation.setText(food.getDesignation());
            price.setText(String.valueOf(food.getPrice()));
            quantity.setText(String.valueOf(food.getQuantity()));
            int total=food.getPrice()*food.getQuantity();
            amount.setText(String.valueOf(total));

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
                int a = Integer.parseInt(price.getText().toString());
                int b = Integer.parseInt(quantity.getText().toString());
                amount.setText("Amount : "+ String.valueOf(a*b));
            }
        });

        Button save = (Button) findViewById(R.id.save_add_food);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(food_id==-1){
                    Log.d("food_insert","inserted");
                     food_class food =new food_class();
                    food.setCicle_id(cicle_id);
                    food.setBuilding_id(building_id);
                    food.setDate(date_text.getText().toString());
                    food.setDesignation(designation.getText().toString());
                    food.setPrice(Integer.parseInt(price.getText().toString()));
                    food.setQuantity(Integer.parseInt(quantity.getText().toString()));
                    handler.createFood(food);
                    List<food_class> food_list=handler.getAllFood(cicle_id,building_id);
                    food_id=food_list.get(food_list.size()-1).getFood_id();
                }
                else{
                    food_class food =new food_class();
                    food.setFood_id(food_id);
                    food.setCicle_id(cicle_id);
                    food.setBuilding_id(building_id);
                    food.setDate(date_text.getText().toString());
                    food.setDesignation(designation.getText().toString());
                    food.setPrice(Integer.parseInt(price.getText().toString()));
                    food.setQuantity(Integer.parseInt(quantity.getText().toString()));
                    int row=handler.updateFood(food);
                    if(row==1){
                        Toast.makeText(getApplicationContext(), "successfully updated", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Error in updating",Toast.LENGTH_SHORT).show();
                    }
                }
                Intent intent=new Intent(getApplicationContext(),ShowFood.class);
                intent.putExtra("id",food_id);
                finish();
                startActivity(intent);
            }
        });

    }

    public void setDate(View view) {
        showDialog(999);
    }


    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year_x, month_x, day_x);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year,int month,int day){
        date_text.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), Food.class);
        Log.d("add_food_in_cicle",String .valueOf(cicle_id));
        Log.d("add_food_in_building",String .valueOf(building_id));
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
        return true;
    }
}
