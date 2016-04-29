package com.example.sneh.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Himanshu on 11/10/2015.
 */
public class DataBaseHandler extends SQLiteOpenHelper
{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = " Camera_imagedb";

    // Contacts table name
    private static final String TABLE_CONTACTS = " Camera_contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IMAGE = "image";

    //
    private static final String TABLE_NOTES = " notes";

    private static final String KEY_ID_NOTES="id";

    private static final String KEY_NOTES="notes";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_Notes_TABLE = "create table if not exists " + TABLE_NOTES + "("
                + KEY_ID_NOTES + " INTEGER PRIMARY KEY , " + KEY_NOTES + " TEXT"
                 + ")";
        Log.d("table_building_info_sql", CREATE_Notes_TABLE);
        db.execSQL(CREATE_Notes_TABLE);
        Log.d("building_info_status", "created");

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_IMAGE + " BLOB" + ")";

        Log.d("table_building__sql", CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.d("building_info_status", "crted");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NOTES);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read) Operations
     */
    public void addnotes(notes_file n)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(KEY_NOTES, n.notes);
        db.insert(TABLE_NOTES, null, values);
        db.close();
    }


    public// Adding new contact
    void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact._name); // Contact Name
        values.put(KEY_IMAGE, contact._image); // Contact Phone

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_IMAGE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getBlob(1));

        // return contact
        return contact;

    }
    public String getNotes() {
        String selectQuery = "SELECT * FROM " + TABLE_NOTES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String s = "Hello";
        if (cursor.moveToLast()) {
            s=cursor.getString(1);
            Log.d("DB_S: ",s);
        }
        return s;
    }

    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM "+TABLE_CONTACTS+ " ORDER BY name";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setImage(cursor.getBlob(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        // close inserting data from database
        db.close();
        // return contact list
        return contactList;
    }
}