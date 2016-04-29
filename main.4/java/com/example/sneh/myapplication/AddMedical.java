package com.example.sneh.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddMedical extends AppCompatActivity {

    int year_x, month_x, day_x;
    private Calendar calendar;
    private TextView date_text;

    EditText product,price,comment;
    Button save;
    int cicle_id,building_id,medical_id;
    int operation_name,operation_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medical);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_medical);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] array_spinner = new String[8];
        array_spinner[0] = "Type(Select)";
        array_spinner[1] = "type1";
        array_spinner[2] = "Type 2";
        array_spinner[3] = "Type 3";
        array_spinner[4] = "Type 4";
        array_spinner[5] = "Type 5";
        array_spinner[6] = "Type 6";
        array_spinner[7] = "Other";

        final Spinner spinner = (Spinner) findViewById(R.id.add_medical_op_name);
        //final EditText other=(EditText)findViewById(R.id.show_building_other);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        final Spinner spinner2 = (Spinner) findViewById(R.id.add_medical_type);
        //final EditText other=(EditText)findViewById(R.id.show_building_other);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(0);

        date_text = (TextView) findViewById(R.id.add_medical_start_date);
        product = (EditText) findViewById(R.id.add_medical_product);
        price = (EditText) findViewById(R.id.add_medical_price);
        comment = (EditText) findViewById(R.id.add_medical_comment);

        calendar= Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year_x, month_x + 1, day_x);

        //Setting db_handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        /*getting building_id and cicle_id*/
        Intent intent=getIntent();
        cicle_id=intent.getIntExtra("cicle_id", -1);
        building_id=intent.getIntExtra("building_id",-1);
        medical_id=intent.getIntExtra("medical_id",-1);

        final medical_ medical=handler.getMedical(medical_id);

        for(int i=0; i<array_spinner.length; i++){
            if(array_spinner[i].equals(medical.getOperation_name()))
                operation_name = i;
        }

        for(int i=0; i<array_spinner.length; i++){
            if(array_spinner[i].equals(medical.getOperation_type()))
                operation_type = i;
        }

        if(medical_id!=-1){
            product.setText(medical.getProduct());
            date_text.setText(medical.getDate());
            comment.setText(medical.getComment());
            price.setText(String.valueOf(medical.getPrice()));
            spinner.setSelection(operation_name);
            spinner2.setSelection(operation_type);
        }
        Log.d("Add_equipment_medical_id", String.valueOf(medical_id));
        Log.d("Add_equipment_cicle_id", String.valueOf(cicle_id));
        Log.d("Add_equip_building_id", String.valueOf(building_id));


        save = (Button) findViewById(R.id.save_add_medical);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(medical_id!=-1){

                    medical.setCicle_id(cicle_id);
                    medical.setBuilding_id(building_id);
                    medical.setOperation_name(spinner.getSelectedItem().toString());
                    medical.setOperation_type(spinner2.getSelectedItem().toString());
                    medical.setComment(comment.getText().toString());
                    medical.setDate(date_text.getText().toString());
                    medical.setProduct(product.getText().toString());
                    medical.setPrice(Integer.parseInt(price.getText().toString()));
                    handler.updateMedical(medical);
                    Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),Medical.class);
                    intent.putExtra("cicle_id", cicle_id);
                    intent.putExtra("building_id", building_id);
                    finish();
                    startActivity(intent);
                }
                else {

                    medical.setCicle_id(cicle_id);
                    medical.setBuilding_id(building_id);
                    medical.setOperation_name(spinner.getSelectedItem().toString());
                    medical.setOperation_type(spinner2.getSelectedItem().toString());
                    medical.setComment(comment.getText().toString());
                    medical.setDate(date_text.getText().toString());
                    medical.setProduct(product.getText().toString());
                    medical.setPrice(Integer.parseInt(price.getText().toString()));
                    handler.createMedical(medical);
                    Toast.makeText(getApplicationContext(),"Successfully created",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Medical.class);
                    intent.putExtra("cicle_id", cicle_id);
                    intent.putExtra("building_id", building_id);
                    Toast.makeText(getApplicationContext(),price.getText().toString(),Toast.LENGTH_SHORT).show();
                    Log.d("price......medical",price.getText().toString());
                    finish();
                    startActivity(intent);
                }
            }
        });

        Log.d("price_medical", String.valueOf(medical.getPrice()));

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
        Intent intent = new Intent(getApplicationContext(), Medical.class);
        //intent.putExtra("cicle_id", cicle_id);
        //intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
        return true;
    }
}
