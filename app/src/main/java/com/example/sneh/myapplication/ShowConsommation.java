package com.example.sneh.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ShowConsommation extends AppCompatActivity {
    int con_id;
    TextView start_date,designation,quantity,food,type,end_date;
    consommation_ con;
    int cicle_id,cicle_sql,building_id,building_sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_consommation);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting db
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class sc = handler.get_all_setting();




        //setting up intent
        Intent intent=getIntent();
        con_id=intent.getIntExtra("id", -1);
        con=handler.getCon(con_id);

        Log.d("show_con_con_id", String.valueOf(con_id));
        Log.d("show_con_cicle_id", String.valueOf(con.getConsommation_id()));
        Log.d("show_con_building_id", String.valueOf(con.getBuilding_id()));
        Log.d("show_con_cicle_sql", String.valueOf(con.getSql_cicle_id()));
        Log.d("show_con_building_sql", String.valueOf(con.getSql_building_id()));
        Log.d("show_con_food_sql",String.valueOf(con.getSql_consommation_id()));
        Log.d("show_con_user_id",String.valueOf(con.getUser_id()));
        Log.d("show_con_flag",String.valueOf(con.getFlag()));

        //setting textview
        start_date=(TextView)findViewById(R.id.show_con_start_date);
        designation=(TextView)findViewById(R.id.show_con_designation);
        food=(TextView)findViewById(R.id.show_con_food);
        quantity=(TextView)findViewById(R.id.show_con_quantity);
        type=(TextView)findViewById(R.id.show_con_type);
        end_date=(TextView)findViewById(R.id.show_con_end_date);

        start_date.setText(con.getDate_start());
        designation.setText(con.getDesignation());
        food.setText(con.getFood_list());
        quantity.setText(String.valueOf(con.getQuantity()));
        type.setText(con.getPer());
        end_date.setText(con.getDate_end());

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_con_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowConsommation.this, AddConsommation.class);
                intent.putExtra("cicle_id", con.getCicle_id());
                intent.putExtra("building_id", con.getBuilding_id());
                intent.putExtra("con_id", con_id);
                finish();
                startActivity(intent);
            }
        });
        FloatingActionButton del = (FloatingActionButton) findViewById(R.id.show_con_del);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle(getResources().getString(R.string.alert_delete_title));
        builder.setMessage(getResources().getString(R.string.alert_delete_message));
        builder.setPositiveButton(getResources().getString(R.string.alert_detete_button_yes), new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                //write whole functionaliy that your delete button is doing
                Log.d("show_con_flagqw::"+String.valueOf(con.getConsommation_id()),String.valueOf(con.getFlag()));
                task_class taskClass=handler.getTask_by_con(con.getConsommation_id());
                Log.d("task_id:", String.valueOf(taskClass.getTask_id()));
                check_if_set(taskClass.getTask_id());
                handler.deletetask(taskClass);
                if(con.getFlag()==0){
                    handler.deleteCon(con);
                }else{
                    con.setFlag(-1);
                    handler.updateCon(con);
                }



                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_delete_entry), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShowConsommation.this, FoodConsommation.class);
                intent.putExtra("cicle_id",con.getCicle_id());
                intent.putExtra("building_id",con.getBuilding_id());
                finish();
                startActivity(intent);
            }

        });

        builder.setNegativeButton(getResources().getString(R.string.alert_detete_button_no), new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });

        final AlertDialog alert = builder.create();


        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }
        });

    }
    public void check_if_set(int id)
    {
        Intent intent=new Intent(this,NotificationPublisher.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this, id, intent,0);
        AlarmManager alarmManager=(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ShowConsommation.this, FoodConsommation.class);
        intent.putExtra("cicle_id",con.getCicle_id());
        intent.putExtra("building_id",con.getBuilding_id());
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ShowConsommation.this, FoodConsommation.class);
        intent.putExtra("cicle_id",con.getCicle_id());
        intent.putExtra("building_id",con.getBuilding_id());
        finish();
        startActivity(intent);
    }

}