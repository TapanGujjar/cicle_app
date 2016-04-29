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

public class AddDeath extends AppCompatActivity {

    EditText no;
    TextView date;
    Button save;
    int year_x, month_x, day_x;
    private TextView date_text;
    private Calendar calendar;
    int building_id,cicle_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_death);

        date_text = (TextView) findViewById(R.id.add_death_start_date);
        calendar=Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year_x, month_x + 1, day_x);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_death);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        save = (Button) findViewById(R.id.save_add_death);
        date=(TextView)findViewById(R.id.add_death_start_date);
        no=(EditText)findViewById(R.id.add_death_no);

        //Setting db_handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        /*getting building_id and cicle_id*/
        Intent intent=getIntent();
        cicle_id=intent.getIntExtra("cicle_id", -1);
        building_id=intent.getIntExtra("building_id",-1);
        final int death_id=intent.getIntExtra("death_id",-1);

        if(death_id!=-1){
            death_ death=handler.getDeath(death_id);
            date.setText(death.getDate());
            no.setText(String.valueOf(death.getDeath_no()));
        }
        Log.d("Add_death_death_id", String.valueOf(death_id));
        Log.d("Add_death_cicle_id", String.valueOf(cicle_id));
        Log.d("Add_death_building_id", String.valueOf(building_id));



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(death_id!=-1){
                    death_ death=new death_();
                    death.setDeath_id(death_id);
                    death.setCicle_id(cicle_id);
                    death.setBuilding_id(building_id);
                    death.setDate(date.getText().toString());
                    death.setDeath_no(Integer.parseInt(no.getText().toString()));
                    handler.updateDeath(death);
                    Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),Death.class);
                    intent.putExtra("cicle_id", cicle_id);
                    intent.putExtra("building_id", building_id);
                    finish();
                    startActivity(intent);
                }
                else {
                    death_ death=new death_();
                    death.setCicle_id(cicle_id);
                    death.setBuilding_id(building_id);
                    death.setDate(date.getText().toString());
                    death.setDeath_no(Integer.parseInt(no.getText().toString()));
                    handler.createDeath(death);
                    Toast.makeText(getApplicationContext(),"Successfully created",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Death.class);
                    intent.putExtra("cicle_id", cicle_id);
                    intent.putExtra("building_id", building_id);
                    finish();
                    startActivity(intent);
                }
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
        Intent intent = new Intent(getApplicationContext(), Death.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
        return true;
    }
}
