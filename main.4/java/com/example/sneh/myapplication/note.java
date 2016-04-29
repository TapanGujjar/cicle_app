package com.example.sneh.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class note extends AppCompatActivity {
    DataBaseHandler db;
    EditText notes_entry;
    Button save;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        notes_entry=(EditText)findViewById(R.id.notes_enrty);
        save=(Button)findViewById(R.id.save_button_notes);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db=new DataBaseHandler(this);

        String s=db.getNotes();

        notes_entry.setText(s);
        Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              notes_file n=new notes_file();
                n.setNotes(notes_entry.getText().toString());
                n.setId(id);
                db.addnotes(n);
                Toast.makeText(getBaseContext(),n.getNotes(),Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}
