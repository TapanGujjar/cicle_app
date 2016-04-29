package com.example.sneh.myapplication;

/**
 * Created by Himanshu on 11/10/2015.
 */
public class notes_file {

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    String notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

    notes_file(int id,String notes)
    {
        this.id=id;
        this.notes=notes;
    }
    notes_file(String notes)
    {
        this.notes=notes;
    }
    notes_file()
    {

    }
}
