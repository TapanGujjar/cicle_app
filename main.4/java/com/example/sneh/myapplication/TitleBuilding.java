package com.example.sneh.myapplication;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class TitleBuilding extends AppCompatActivity {

    int building_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_building);
        //Setting up db

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        LinearLayout equipment = (LinearLayout) findViewById(R.id.equipment);
        LinearLayout animals = (LinearLayout) findViewById(R.id.animals);
        LinearLayout worker = (LinearLayout) findViewById(R.id.workers);
        LinearLayout food = (LinearLayout) findViewById(R.id.food);
        LinearLayout medical = (LinearLayout) findViewById(R.id.medical);
        LinearLayout finianse = (LinearLayout) findViewById(R.id.finianse);
        LinearLayout death = (LinearLayout) findViewById(R.id.death);
        LinearLayout alive = (LinearLayout) findViewById(R.id.alive);
        LinearLayout egg = (LinearLayout) findViewById(R.id.egg);
        LinearLayout temp = (LinearLayout) findViewById(R.id.temp);
        LinearLayout note = (LinearLayout) findViewById(R.id.note);
        LinearLayout pic = (LinearLayout) findViewById(R.id.pics);

        /* getting the building_id */
        Intent intent=getIntent();
        building_id=intent.getIntExtra("building_id", -1);
        Log.d("Title_building_id", String.valueOf(building_id));
        final building_class building=handler.getBuilding(building_id);
        Log.d("title_building_cicle_id",String.valueOf(building.getCicle_id()));


        equipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this, Equipment.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                startActivity(intent);

            }
        });

        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this,Animals.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                startActivity(intent);
            }
        });

        worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this, Worker.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                startActivity(intent);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this, Food.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                startActivity(intent);
            }
        });

        medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this, Medical.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                startActivity(intent);
            }
        });

        finianse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TitleBuilding.this, Finianse.class));
            }
        });

        egg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this, egg_extra_screen.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                startActivity(intent);
            }
        });

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TitleBuilding.this, Temp.class));
            }
        });

        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TitleBuilding.this, note.class));
            }
        });

        death.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this, Death.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                startActivity(intent);
            }
        });

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TitleBuilding.this, CameraPictureActivity.class));
            }
        });

        alive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this, Alive.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                //finish();
                Intent i = new Intent(TitleBuilding.this, ShowBuilding.class);
                i.putExtra("building_id", building_id);
                startActivity(i);

                return true;

            default:
                finish();
                return true;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_title_building, menu);
        return true;
    }

}
