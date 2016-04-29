package com.example.sneh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Setting extends AppCompatActivity {
    String[] array_spinner;
    String[] array_notif;
    String[] array_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        final Spinner s_notif=(Spinner)findViewById(R.id.setting_notification);
        array_notif=new String[3];
        array_notif[0]="Notification(YES/NO)";
        array_notif[1]="YES";
        array_notif[2]="NO";
        final ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_notif);
        s_notif.setAdapter(adapter2);
        s_notif.setSelection(0);
        //Country Spinner
        array_spinner= getResources().getStringArray(R.array.entries);

        final Spinner s = (Spinner) findViewById(R.id.setting_country);
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        s.setAdapter(adapter);
        s.setSelection(0);

        array_money=getResources().getStringArray(R.array.values);
        final Spinner s_money=(Spinner)findViewById(R.id.setting_money_format);

        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_money);
        s_money.setAdapter(adapter3);
        s_money.setSelection(0);
        Button save_button=(Button)findViewById(R.id.setting_save);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int n = s.getSelectedItemPosition();
                s_money.setSelection(n);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        s_money.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int n = s_money.getSelectedItemPosition();
                s.setSelection(n);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Notification Spinner

        //Money Unit



        final setting_class setting=new setting_class();
        //

        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class setting1=handler.get_all_setting();
        int row=handler.delete_setting(setting1);
        if(setting1==null){
            Toast.makeText(getApplicationContext(),"null",Toast.LENGTH_SHORT).show();
        }
        else if(setting1!=null){
            Toast.makeText(getApplicationContext(),"not_null",Toast.LENGTH_SHORT).show();
        }

        if (setting1 != null) {
            Log.d("notif", String.valueOf(setting1.getNotification()));
            Log.d("country", setting1.getCountry());
            Log.d("money", setting1.getMoney_format());
            for (int i = 0; i < array_spinner.length; i++) {
                if (array_spinner[i].equals(setting1.getCountry())) {
                    s.setSelection(i);
                }
            }
            for(int i=0;i<array_notif.length;i++){
                if(array_notif[i].equals(setting1.getNotification())){
                    s_notif.setSelection(i);
                }
            }

            for (int i = 0; i < array_money.length; i++) {
                if (array_money[i].equals(setting1.getMoney_format())) {
                    s_money.setSelection(i);
                }
            }
        }
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setting.setNotification(s_notif.getSelectedItem().toString());
                setting.setCountry(s.getSelectedItem().toString());
                setting.setMoney_format(s_money.getSelectedItem().toString());
                handler.insert_setting(setting);
                Log.d("select_notif", setting.getNotification());
                Log.d("select country",setting.getCountry());
                Log.d("select monety",setting.getMoney_format());
                Intent intent=new Intent(getApplicationContext(),Tasks.class);
                startActivity(intent);
            }
        });
    }
}