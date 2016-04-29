package com.example.sneh.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class AddExpense extends AppCompatActivity {

    int year_x, month_x, day_x;
    private TextView date_text;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        date_text = (TextView) findViewById(R.id.add_expense_start_date);
        calendar=Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year_x, month_x + 1, day_x);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_expense);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView price = (TextView) findViewById(R.id.price_add_exp);
        final TextView quantity = (TextView) findViewById(R.id.quantity_add_exp);
        final TextView amount = (TextView) findViewById(R.id.amount_add_exp);

        String[] array_spinner = new String[8];
        array_spinner[0] = "Type(Select)";
        array_spinner[1] = "type1";
        array_spinner[2] = "Type 2";
        array_spinner[3] = "Type 3";
        array_spinner[4] = "Type 4";
        array_spinner[5] = "Type 5";
        array_spinner[6] = "Type 6";
        array_spinner[7] = "Type 7";
        Spinner spinner = (Spinner) findViewById(R.id.add_expense_type);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

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
                amount.setText("Amount : " + String.valueOf(a * b));
            }
        });

        Button save = (Button) findViewById(R.id.save_add_expense);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        finish();
        return true;
    }
}
