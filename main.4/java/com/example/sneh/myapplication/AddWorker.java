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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class AddWorker extends AppCompatActivity {
    int worker_id;
    int year_x, month_x, day_x;
    private TextView date_text;
    private Calendar calendar;
    int cicle_id,building_id;
    private EditText name,address,phone,start_date,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);

        date_text = (TextView) findViewById(R.id.add_worker_start_date);
        calendar=Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year_x, month_x + 1, day_x);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_worker);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting intent and getting cicle_id building_id worker_id
        Intent intent=getIntent();
        cicle_id=intent.getIntExtra("cicle_id", -1);
        building_id=intent.getIntExtra("building_id",-1);
         worker_id=intent.getIntExtra("worker_id",-1); //if worker_id!=-1 then update
        Log.d("Add_work_worker_id", String.valueOf(worker_id));
        Log.d("Add_work_cicle_id", String.valueOf(cicle_id));
        Log.d("Add_work_building_id", String.valueOf(building_id));

        //settting up db_handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        //setting up the edit text
        name=(EditText)findViewById(R.id.add_worker_name);
        address=(EditText)findViewById(R.id.add_worker_address);
        phone=(EditText)findViewById(R.id.add_worker_phone);
        price=(EditText)findViewById(R.id.add_worker_price);
        final worker_class worker;
        //setting various edittext for updating
        if(worker_id!=-1){
            worker=handler.getWorker(worker_id);
            name.setText(worker.getName());
            address.setText(worker.getAddress());
            phone.setText(worker.getTel());
            date_text.setText(worker.getDate_start());
            price.setText(String.valueOf(worker.getPrice_per_day()));
        }
        Button save = (Button) findViewById(R.id.add_worker_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(worker_id!=-1){
                    Log.d("add_worker","update");
                    worker_class worker=new worker_class();
                    worker.setWorker_id(worker_id);
                    worker.setCicle_id(cicle_id);
                    worker.setBuilding_id(building_id);
                    worker.setName(name.getText().toString());
                    worker.setAddress(address.getText().toString());
                    worker.setTel(address.getText().toString());
                    worker.setDate_start(date_text.getText().toString());
                    worker.setPrice_per_day(Integer.parseInt(price.getText().toString()));
                    int row=handler.updateWorker(worker);
                    if(row==1){
                        Toast.makeText(getApplicationContext(),"successfully updated",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Error in updating",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Log.d("add_worker", "create_worker");
                    worker_class worker=new worker_class();
                    worker.setCicle_id(cicle_id);
                    worker.setBuilding_id(building_id);
                    worker.setName(name.getText().toString());
                    worker.setAddress(address.getText().toString());
                    worker.setTel(address.getText().toString());
                    worker.setDate_start(date_text.getText().toString());
                    worker.setPrice_per_day(Integer.parseInt(price.getText().toString()));
                    handler.CreateWorker(worker);
                    List<worker_class> worker_list=handler.get_all_worker(cicle_id,building_id);
                    worker_id=worker_list.get(worker_list.size()-1).getWorker_id();
                }

                Intent intent=new Intent(getApplicationContext(),ShowWorker.class);
                intent.putExtra("id",worker_id);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), Worker.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
        return true;
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

}
