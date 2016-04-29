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
 * Created by starsilver on 5/11/15.
 */
public class db_handler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="cicle_db";
    //table_user_info
    private static final String TABLE_USER_INFO="login_info";
    private static final String KEY_ID="id";
    private static final String KEY_F_NAME="first_name";
    private static final String KEY_L_NAME="last_name";
    private static final String KEY_PASSWORD="user_password";
    private static final String KEY_EMAIL="user_email";

    //table_cicle_info
    private static final String TABLE_CICLE_INFO="cicle_info";
    private static final String KEY_CICLE_ID="cicle_id";
    private static final String KEY_CICLE_TITLE="cicle_title";
    private static final String KEY_CICLE_OWNER="cicle_owner";
    private static final String KEY_CICLE_LOCATION="cicle_location";
    private static final String KEY_CICLE_START_DATE="cicle_start_date";
    private static final String KEY_CICLE_TYPE="cicle_type";
    private static final String KEY_CICLE_DONE="cicle_done";

    //table building info
    private static final String TABLE_BUILDING_INFO="building_info";
    private static final String KEY_BUILDING_ID="building_id";
    private static final String KEY_BUILDING_CICLE_ID="building_cicle_id";
    private static final String KEY_BUILDING_TITLE="building_title";
    private static final String KEY_BUILDING_CAPACITY="building_capacity";
    private static final String KEY_BUILDING_TYPE="building_type";
    private static final String KEY_BUILDING_OTHER="building_other";

    //table equipment info
    private static final String TABLE_EQUIP_INFO="equipment_info";
    private static final String KEY_EQUIP_ID="equipment_id";
    private static final String KEY_EQUIP_CICLE_ID="equipment_cicle_id";
    private static final String KEY_EQUIP_BUILDING_ID="equipment_building_id";
    private static final String KEY_EQUIP_TYPE="equipment_type";
    private static final String KEY_EQUIP_DESIGNATION="equipment_designation";
    private static final String KEY_EQUIP_QUANTITY="building_other";
    private static final String KEY_EQUIP_PRICE="building_equipment_price";

    //table worker_info
    private static final String TABLE_WORKER_INFO="worker_info";
    private static final String KEY_WORKER_ID="worker_id";
    private static final String KEY_WORKER_CICLE_ID="worker_cicle_id";
    private static final String KEY_WORKER_BUILDING_ID="worker_building_id";
    private static final String KEY_WORKER_NAME="worker_name";
    private static final String KEY_WORKER_ADDRESS="worker_address";
    private static final String KEY_WORKER_PHONE="worker_phone";
    private static final String KEY_WORKER_START_DATE="worker_date";
    private static final String KEY_WORKER_PRICE="worker_price";

    //table animal_info
    private static final String TABLE_ANIMAL_INFO="animal_info";
    private static final String KEY_ANIMAL_ID="animal_id";
    private static final String KEY_ANIMAL_CICLE_ID="animal_cicle_id";
    private static final String KEY_ANIMAL_BUILDING_ID="animal_building_id";
    private static final String KEY_ANIMAL_TYPE="animal_type";
    private static final String KEY_ANIMAL_OTHER="animal_other";
    private static final String KEY_ANIMAL_QUANTITY="animal_quantity";
    private static final String KEY_ANIMAL_PRICE="animal_price";

    //table_food_info
    private static final String TABLE_FOOD_INFO="food_info";
    private static final String KEY_FOOD_ID="food_id";
    private static final String KEY_FOOD_CICLE_ID="food_cicle_id";
    private static final String KEY_FOOD_BUILDING_ID="food_building_id";
    private static final String KEY_FOOD_DATE="food_date";
    private static final String KEY_FOOD_DESIGNATION="food_designation";
    private static final String KEY_FOOD_QUANTITY="food_quantity";
    private static final String KEY_FOOD_PRICE="food_price";

    //table death info
    private static final String TABLE_DEATH_INFO="deatht_info";
    private static final String KEY_DEATH_ID="deatht_id";
    private static final String KEY_DEATH_CICLE_ID="death_cicle_id";
    private static final String KEY_DEATH_BUILDING_ID="death_building_id";
    private static final String KEY_DEATH_NO="death_type";
    private static final String KEY_DEATH_DATE="death_designation";

    //table egg info
    private static final String TABLE_EGG_INFO="egg_info";
    private static final String KEY_EGG_ID="egg_id";
    private static final String KEY_EGG_CICLE_ID="egg_cicle_id";
    private static final String KEY_EGG_BUILDING_ID="egg_building_id";
    private static final String KEY_EGG_DATE="egg_date";
    private static final String KEY_EGG_TYPE="egg_type";
    private static final String KEY_EGG_NUMBER="egg_number";

    //table Medical_info
    private static final String TABLE_MEDICAL_INFO="medical_info";
    private static final String KEY_MEDICAL_ID="medical_id";
    private static final String KEY_MEDICAL_CICLE_ID="medical_cicle_id";
    private static final String KEY_MEDICAL_BUILDING_ID="medical_building_id";
    private static final String KEY_MEDICAL_OPERATION_TYPE="medical_operation_type";
    private static final String KEY_MEDICAL_OPERATION_NAME="medical_operation_name";
    private static final String KEY_MEDICAL_PRODUCT="medical_product";
    private static final String KEY_MEDICAL_DATE="medical_date";
    private static final String KEY_MEDICAL_COMMENT="medical_comment";
    private static final String KEY_MEDICAL_PRICE="medical_price";

    //table_setting_info
    private static final String TABLE_SETTING_INFO="setting_info";
    private static final String KEY_SETTING_NOTIFICATION="setting_notification";
    private static final String KEY_SETTING_COUNTRY="setting_country";
    private static final String KEY_SETTING_MONEY="setting_money_format";

    //table_notes_info
    private static final String TABLE_NOTES_INFO="setting_info";
    private static final String KEY_NOTES_ID="setting_notification";
    private static final String KEY_NOTES_CICLE_ID="setting_country";
    private static final String KEY_NOTES_BUILDING_ID="setting_country";
    private static final String KEY_NOTES="setting_country";

    String sql;



    public  db_handler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){}

    public void onCreateTable(SQLiteDatabase db){
        sql="CREATE TABLE IF NOT EXISTS " + TABLE_USER_INFO + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_F_NAME+ " VARCHAR, "
                + KEY_L_NAME+ " VARCHAR, "
                + KEY_PASSWORD + " VARCHAR, "
                + KEY_EMAIL + " VARCHAR" +
                ")";
        Log.d("table_user_info_sql", sql);
        db.execSQL(sql);
        Log.d("user_info_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_CICLE_INFO + "("
                + KEY_CICLE_ID + " INTEGER PRIMARY KEY, "
                + KEY_CICLE_TITLE+ " VARCHAR, "
                + KEY_CICLE_OWNER + " VARCHAR, "
                + KEY_CICLE_LOCATION + " VARCHAR," +
                KEY_CICLE_START_DATE + " VARCHAR, " +
                KEY_CICLE_TYPE+ " VARCHAR, " +
                KEY_CICLE_DONE+ " INTEGER " +
                ")";
        Log.d("table_user_info_sql", sql);
        db.execSQL(sql);
        Log.d("user_info_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_BUILDING_INFO + "("
                + KEY_BUILDING_ID + " INTEGER PRIMARY KEY, "
                + KEY_BUILDING_CICLE_ID+ " INTEGER, "
                + KEY_BUILDING_TITLE + " VARCHAR, "
                + KEY_BUILDING_CAPACITY + " INTEGER," +
                KEY_BUILDING_TYPE + " VARCHAR, " +
                KEY_BUILDING_OTHER+ " VARCHAR " +
                ")";
        Log.d("table_building_info_sql", sql);
        db.execSQL(sql);
        Log.d("building_info_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_EQUIP_INFO + "("
                + KEY_EQUIP_ID + " INTEGER PRIMARY KEY, "
                + KEY_EQUIP_CICLE_ID+ " INTEGER, "
                + KEY_EQUIP_BUILDING_ID + " INTEGER, "
                + KEY_EQUIP_TYPE + " VARCHAR," +
                KEY_EQUIP_DESIGNATION + " VARCHAR, " +
                KEY_EQUIP_QUANTITY+ " INTEGER, " +
                KEY_EQUIP_PRICE+ " INTEGER " +
                ")";
        Log.d("table_equip_info_sql", sql);
        db.execSQL(sql);
        Log.d("equip_info_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_WORKER_INFO + "("
                + KEY_WORKER_ID + " INTEGER PRIMARY KEY, "
                + KEY_WORKER_CICLE_ID+ " INTEGER, "
                + KEY_WORKER_BUILDING_ID + " INTEGER, "
                + KEY_WORKER_NAME + " VARCHAR," +
                KEY_WORKER_ADDRESS + " VARCHAR, " +
                KEY_WORKER_PHONE+ " VARCHAR, " +
                KEY_WORKER_START_DATE+ " VARCHAR, " +
                KEY_WORKER_PRICE+ " INTEGER " +
                ")";
        Log.d("table_worker_info_sql", sql);
        db.execSQL(sql);
        Log.d("equip_worker_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_ANIMAL_INFO + "("
                + KEY_ANIMAL_ID + " INTEGER PRIMARY KEY, "
                + KEY_ANIMAL_CICLE_ID+ " INTEGER, "
                + KEY_ANIMAL_BUILDING_ID + " INTEGER, "
                + KEY_ANIMAL_TYPE + " VARCHAR," +
                KEY_ANIMAL_OTHER + " VARCHAR, " +
                KEY_ANIMAL_QUANTITY+ " INTEGER, " +
                KEY_ANIMAL_PRICE+ " VARCHAR " +
                ")";
        Log.d("table_animal_info_sql",sql);
        db.execSQL(sql);
        Log.d("animal_info_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_FOOD_INFO + "("
                + KEY_FOOD_ID + " INTEGER PRIMARY KEY, "
                + KEY_FOOD_CICLE_ID+ " INTEGER, "
                + KEY_FOOD_BUILDING_ID + " INTEGER, "
                + KEY_FOOD_DATE + " VARCHAR," +
                KEY_FOOD_DESIGNATION + " VARCHAR, " +
                KEY_FOOD_QUANTITY+ " INTEGER, " +
                KEY_FOOD_PRICE+ " VARCHAR " +
                ")";
        Log.d("table_food_info_sql", sql);
        db.execSQL(sql);
        Log.d("animal_food_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_DEATH_INFO + "("
                + KEY_DEATH_ID + " INTEGER PRIMARY KEY, "
                + KEY_DEATH_CICLE_ID+ " INTEGER, "
                + KEY_DEATH_BUILDING_ID + " INTEGER, "
                + KEY_DEATH_DATE + " VARCHAR," +
                KEY_DEATH_NO + " INTEGER " +
                ")";
        Log.d("table_egg_info_sql", sql);
        db.execSQL(sql);
        Log.d("egg_info_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_EGG_INFO + "("
                + KEY_EGG_ID + " INTEGER PRIMARY KEY, "
                + KEY_EGG_CICLE_ID+ " INTEGER, "
                + KEY_EGG_BUILDING_ID + " INTEGER, "
                + KEY_EGG_DATE + " VARCHAR," +
                KEY_EGG_NUMBER + " INTEGER, " +
                KEY_EGG_TYPE + " VARCHAR "+
                ")";
        Log.d("table_egg_info_sql", sql);
        db.execSQL(sql);
        Log.d("gg_info_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_MEDICAL_INFO + "("
                + KEY_MEDICAL_ID + " INTEGER PRIMARY KEY, "
                + KEY_MEDICAL_CICLE_ID+ " INTEGER, "
                + KEY_MEDICAL_BUILDING_ID + " INTEGER, "
                + KEY_MEDICAL_OPERATION_TYPE + " VARCHAR," +
                KEY_MEDICAL_OPERATION_NAME + " VARCHAR, " +
                KEY_MEDICAL_DATE+ " VARCHAR, " +
                KEY_MEDICAL_COMMENT+ " VARCHAR, " +
                KEY_MEDICAL_PRODUCT+ " VARCHAR, " +
                KEY_MEDICAL_PRICE+ " INTEGER " +
                ")";
        Log.d("table_medical_info_sql",sql);
        db.execSQL(sql);
        Log.d("animal_medical_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_SETTING_INFO + "("
                + KEY_SETTING_NOTIFICATION+ " INTEGER, "
                + KEY_SETTING_COUNTRY+ " VARCHAR, "
                + KEY_SETTING_MONEY + " VARCHAR"+
                ")";
        Log.d("table_setting_info_sql",sql);
        db.execSQL(sql);
        Log.d("table_setting_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_NOTES_INFO + "("
                + KEY_NOTES_ID+ " INTEGER PRIMARY KEY, "
                + KEY_NOTES_CICLE_ID+ " INTEGER, "
                + KEY_NOTES_BUILDING_ID + " INTEGER, "+
                KEY_NOTES+ " TEXT"+
                ")";
        Log.d("table_setting_info_sql",sql);
        db.execSQL(sql);
        Log.d("table_setting_status","created");

    }

    public void onUpgrade(SQLiteDatabase db,int previous_version,int new_version){

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER_INFO);
        onCreate(db);
    }

    public int check_first_name_exists(String fname){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_USER_INFO+" WHERE "+KEY_F_NAME+" =?";
        Cursor cursor=db.rawQuery(sql, new String[]{fname});

        if(cursor!=null){
            if(cursor.getCount()==1){
                return 1;
            }
            else
                return -1;


        }
        return -2;
    }

    public int check_last_name_exists(String lname){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_USER_INFO+" WHERE "+KEY_L_NAME+" =?";
        Cursor cursor=db.rawQuery(sql, new String[]{lname});
        int count;
        if(cursor!=null){
            if(cursor.getCount()==1){
                return 1;
            }
            else
                return -1;


        }
        return -2;
    }

    public int check_email_exists(String email){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_USER_INFO+" WHERE "+KEY_EMAIL+"=?";
        Cursor cursor=db.rawQuery(sql, new String[]{email});

        if(cursor!=null){
            if(cursor.getCount()==1){
                return 1;
            }
            else
                return -1;


        }
        return -2;
    }

    public String InsertUser(String fname,String lname,String password,String email){
        int firstname_exists=check_first_name_exists(fname);
        int lastame_exists=check_last_name_exists(lname);
        int email_exists=check_email_exists(email);
        SQLiteDatabase db=getWritableDatabase();
        if((firstname_exists==1 && lastame_exists==1) || email_exists==1){
            return "username/email already exists exists";
        }
        else{
            ContentValues values=new ContentValues();
            values.put(KEY_F_NAME,fname);
            values.put(KEY_L_NAME,lname);
            values.put(KEY_PASSWORD,password);
            values.put(KEY_EMAIL,email);
            db.insert(TABLE_USER_INFO,null,values);
            return "Success";
        }
    }

    public int check_user_exists(String email_id,String password){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_USER_INFO+" WHERE "+KEY_EMAIL+"=? AND "+KEY_PASSWORD+"=?";
        Cursor cursor=db.rawQuery(sql, new String[]{email_id, password});
        if(cursor!=null){
            if(cursor.getCount()==1){
                return 1;
            }
            else
                return -1;
        }
        return -2;
    }



    //Cicle
    public void CreateCicle(cicle cicle){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_CICLE_TITLE,cicle.getTitle());
        values.put(KEY_CICLE_OWNER,cicle.getOwner());
        values.put(KEY_CICLE_LOCATION,cicle.getLocation());
        values.put(KEY_CICLE_START_DATE,cicle.getStart_date());
        values.put(KEY_CICLE_TYPE,cicle.getType());
        values.put(KEY_CICLE_DONE,cicle.getDone());
        db.insert(TABLE_CICLE_INFO, null, values);
        Log.d("cicle_inserted",cicle.getTitle());
    }

    public cicle getCicle(int id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_CICLE_INFO+" WHERE "+KEY_CICLE_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(id)});
        cicle cicle=new cicle();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                Log.d("cicle_found",String.valueOf(cursor.getInt(0)));

                cicle.setCicle_id(cursor.getInt(0));
                cicle.setTitle(cursor.getString(1));
                cicle.setOwner(cursor.getString(2));
                cicle.setLocation(cursor.getString(3));
                cicle.setStart_date(cursor.getString(4));
                cicle.setType(cursor.getString(5));
                cicle.setDone(cursor.getInt(6));
            }
        }
        return  cicle;

    }

    public int updateCicle(cicle cicle){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_CICLE_ID,cicle.getCicle_id());
        values.put(KEY_CICLE_TITLE,cicle.getTitle());
        values.put(KEY_CICLE_OWNER,cicle.getOwner());
        values.put(KEY_CICLE_LOCATION,cicle.getLocation());
        values.put(KEY_CICLE_START_DATE,cicle.getStart_date());
        values.put(KEY_CICLE_TYPE, cicle.getType());
        values.put(KEY_CICLE_DONE, cicle.getDone());
        int id=db.update(TABLE_CICLE_INFO, values, KEY_CICLE_ID + "=?", new String[]{String.valueOf(cicle.getCicle_id())});
        Log.d("cicle_updated", cicle.getTitle());
        return id;
    }

    public void deleteCicle(cicle cicle){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_CICLE_INFO, KEY_CICLE_ID + "=?", new String[]{String.valueOf((cicle.getCicle_id()))});
        Log.d("cicle deleted", cicle.getTitle());
        db.close();
    }

    public List<cicle> get_all_not_done(){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_CICLE_INFO+" WHERE "+KEY_CICLE_DONE+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(1)});
        List<cicle> cicle_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    cicle cicle=new cicle();
                    cicle.setCicle_id(cursor.getInt(0));
                    cicle.setTitle(cursor.getString(1));
                    cicle.setOwner(cursor.getString(2));
                    cicle.setLocation(cursor.getString(3));
                    cicle.setStart_date(cursor.getString(4));
                    cicle.setType(cursor.getString(5));
                    cicle.setDone(cursor.getInt(6));
                    cicle_list.add(cicle);
                }while(cursor.moveToNext());
                Log.d("cicle_get_all_done",String.valueOf(cursor.getCount()));
            }
        }
        return cicle_list;
    }

    public List<cicle> get_all_done(){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_CICLE_INFO+" WHERE "+KEY_CICLE_DONE+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(2)});
        List<cicle> cicle_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    cicle cicle=new cicle();
                    cicle.setCicle_id(cursor.getInt(0));
                    cicle.setTitle(cursor.getString(1));
                    cicle.setOwner(cursor.getString(2));
                    cicle.setLocation(cursor.getString(3));
                    cicle.setStart_date(cursor.getString(4));
                    cicle.setType(cursor.getString(5));
                    cicle.setDone(cursor.getInt(6));
                    cicle_list.add(cicle);
                }while(cursor.moveToNext());
                Log.d("cicle_get_all_not_done",String.valueOf(cursor.getCount()));
            }
        }
        return cicle_list;
    }



    //Building
    public void createBuilding(building_class building){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_BUILDING_CICLE_ID,building.getCicle_id());
        values.put(KEY_BUILDING_TITLE,building.getTitle());
        values.put(KEY_BUILDING_CAPACITY,building.getCapacity());
        values.put(KEY_BUILDING_TYPE,building.getType());
        values.put(KEY_BUILDING_OTHER,building.getOther());
        Log.d("building_inserted",building.getTitle());
        db.insert(TABLE_BUILDING_INFO,null,values);
    }

    public building_class getBuilding(int key){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_BUILDING_INFO+" WHERE "+KEY_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(key)});
        building_class building=new building_class();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                building.setBuilding_id(cursor.getInt(0));
                building.setCicle_id(cursor.getInt(1));
                building.setTitle(cursor.getString(2));
                building.setCapacity(cursor.getInt(3));
                building.setType(cursor.getString(4));
                building.setOther(cursor.getString(5));
            }
        }
        return building;
    }

    public List<building_class> get_all_building(int cicle_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM  "+TABLE_BUILDING_INFO+" WHERE "+KEY_BUILDING_CICLE_ID+"=?";
        List<building_class> building_list=new ArrayList<>();
        Cursor cursor=db.rawQuery(sql, new String[]{String.valueOf(cicle_id)});
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do {
                    building_class building = new building_class();
                    building.setBuilding_id(cursor.getInt(0));
                    building.setCicle_id(cursor.getInt(1));
                    building.setTitle(cursor.getString(2));
                    building.setCapacity(cursor.getInt(3));
                    building.setType(cursor.getString(4));
                    building.setOther(cursor.getString(5));
                    building_list.add(building);
                }while (cursor.moveToNext());
            }
        }
        Log.d("all_building", String.valueOf(building_list.size()));

        return building_list;
    }

    public int getBuildingCount(int cicle_id){
        SQLiteDatabase db=getReadableDatabase();
        int count=-1;
        sql="SELECT * FROM "+TABLE_BUILDING_INFO+" WHERE "+KEY_BUILDING_CICLE_ID+"=?";
        Cursor cursor=db.rawQuery(sql, new String[]{String.valueOf(cicle_id)});
        if(cursor!=null){
            count=cursor.getCount();
        }
        return count;
    }

    public int updateBuilding(building_class building){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_BUILDING_ID,building.getBuilding_id());
        values.put(KEY_BUILDING_CICLE_ID,building.getCicle_id());
        values.put(KEY_BUILDING_TITLE,building.getTitle());
        values.put(KEY_BUILDING_CAPACITY,building.getCapacity());
        values.put(KEY_BUILDING_TYPE,building.getType());
        values.put(KEY_BUILDING_OTHER,building.getOther());
        Log.d("building_inserted", building.getTitle());
        int count=db.update(TABLE_CICLE_INFO, values, KEY_BUILDING_ID + "=?", new String[]{String.valueOf(building.getBuilding_id())});
        return count;
    }

    public void deleteBuilding(building_class building){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_BUILDING_INFO, KEY_BUILDING_ID + "=?", new String[]{String.valueOf(building.getBuilding_id())});
        db.close();
    }



    //Equipment
    public void createEquipment(equipment equipment){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_EQUIP_CICLE_ID,equipment.getCicle_id());
        values.put(KEY_EQUIP_BUILDING_ID,equipment.getBuilding_id());
        values.put(KEY_EQUIP_TYPE,equipment.getType());
        values.put(KEY_EQUIP_DESIGNATION,equipment.getDesignation());
        values.put(KEY_EQUIP_QUANTITY,equipment.getQuantity());
        values.put(KEY_EQUIP_PRICE, equipment.getPrice());
        db.insert(TABLE_EQUIP_INFO, null, values);
        Log.d("equip_insert", "inserted");
    }

    public equipment getEquipment(int equip_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_EQUIP_INFO+" WHERE "+KEY_EQUIP_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(equip_id)});
        equipment equipment=new equipment();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                equipment.setEquip_id(cursor.getInt(0));
                equipment.setCicle_id(cursor.getInt(1));
                equipment.setBuilding_id(cursor.getInt(2));
                equipment.setType(cursor.getString(3));
                equipment.setDesignation(cursor.getString(4));
                equipment.setQuantity(cursor.getInt(5));
                equipment.setPrice(cursor.getInt(6));
            }
        }
        Log.d("equipment_get_equipment", equipment.getType());
        return equipment;

    }

    public void deleteEquipment(equipment equipment){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_EQUIP_INFO, KEY_EQUIP_ID + "=?", new String[]{String.valueOf(equipment.getEquip_id())});
        Log.d("equipment_del_status", "deleted");
        db.close();
    }

    public int updateEquipment(equipment equipment){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_EQUIP_CICLE_ID,equipment.getCicle_id());
        values.put(KEY_EQUIP_BUILDING_ID,equipment.getBuilding_id());
        values.put(KEY_EQUIP_TYPE, equipment.getType());
        values.put(KEY_EQUIP_DESIGNATION, equipment.getDesignation());
        values.put(KEY_EQUIP_QUANTITY, equipment.getQuantity());
        values.put(KEY_EQUIP_PRICE, equipment.getPrice());
        int count=db.update(TABLE_EQUIP_INFO,values,KEY_EQUIP_ID+"=?",new String[]{String.valueOf(equipment.getEquip_id())});
        Log.d("equipment_update", String.valueOf(count));
        Log.d("equipment_update_type", equipment.getType());
        Log.d("equipment_update_des", equipment.getDesignation());
        Log.d("equipme_update_equidid", String.valueOf(equipment.getEquip_id()));

        db.close();
        return count;
    }

    public List<equipment> get_all_equipment(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_EQUIP_INFO+" WHERE "+KEY_EQUIP_CICLE_ID+"=? AND "+KEY_EQUIP_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<equipment> equipment_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    equipment equipment=new equipment();
                    equipment.setEquip_id(cursor.getInt(0));
                    equipment.setCicle_id(cursor.getInt(1));
                    equipment.setBuilding_id(cursor.getInt(2));
                    equipment.setType(cursor.getString(3));
                    equipment.setDesignation(cursor.getString(4));
                    equipment.setQuantity(cursor.getInt(5));
                    equipment.setPrice(cursor.getInt(6));
                    equipment_list.add(equipment);
                }while(cursor.moveToNext());
            }
        }
        Log.d("equipment_list", String.valueOf(equipment_list.size()));
        return  equipment_list;
    }



    //Worker
    public void CreateWorker(worker_class worker){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_WORKER_CICLE_ID,worker.getCicle_id());
        values.put(KEY_WORKER_BUILDING_ID,worker.getBuilding_id());
        values.put(KEY_WORKER_NAME,worker.getName());
        values.put(KEY_WORKER_ADDRESS, worker.getAddress());
        values.put(KEY_WORKER_PHONE, worker.getTel());
        values.put(KEY_WORKER_START_DATE, worker.getTel());
        values.put(KEY_WORKER_PRICE, worker.getPrice_per_day());
        db.insert(TABLE_WORKER_INFO, null, values);
        Log.d("create_worker_status", "created");

    }

    public worker_class getWorker(int worker_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_WORKER_INFO+" WHERE "+KEY_WORKER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(worker_id)});
        worker_class worker=null;
        if(cursor!=null){
            Log.d("db_get_worker",String.valueOf(cursor.getCount()));
            if(cursor.moveToFirst()){
                worker=new worker_class();
                worker.setWorker_id(cursor.getInt(0));
                worker.setCicle_id(cursor.getInt(1));
                Log.d("db_get_worker", cursor.getString(3));
                worker.setBuilding_id(cursor.getInt(2));
                worker.setName(cursor.getString(3));
                worker.setAddress(cursor.getString(4));
                worker.setTel(cursor.getString(5));
                worker.setDate_start(cursor.getString(6));
                worker.setPrice_per_day(cursor.getInt(7));
            }
        }
        Log.d("db_get_worker","successfully got");
        return worker;
    }

    public int updateWorker(worker_class worker) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_WORKER_CICLE_ID,worker.getCicle_id());
        values.put(KEY_WORKER_BUILDING_ID,worker.getBuilding_id());
        values.put(KEY_WORKER_NAME,worker.getName());
        values.put(KEY_WORKER_ADDRESS, worker.getAddress());
        values.put(KEY_WORKER_PHONE, worker.getTel());
        values.put(KEY_WORKER_START_DATE, worker.getTel());
        values.put(KEY_WORKER_PRICE, worker.getPrice_per_day());
        int affected_row=0;
        affected_row=db.update(TABLE_WORKER_INFO,values,KEY_WORKER_ID+"=?",new String[]{String.valueOf(worker.getWorker_id())});
        Log.d("db_worker_update_af_row", String.valueOf(affected_row));
        Log.d("db_worker_update_af_row", String.valueOf(affected_row));
        Log.d("db_worker_update_id",String.valueOf(worker.getWorker_id()));
        return affected_row;

    }

    public int deleteWorker(worker_class worker){
        SQLiteDatabase db=getWritableDatabase();
        int affected_row=0;
        affected_row=db.delete(TABLE_WORKER_INFO, KEY_WORKER_ID + "=?", new String[]{String.valueOf(worker.getWorker_id())});
        db.close();
        Log.d("db_deleteworker", String.valueOf(affected_row));
        return affected_row;
    }

    public List<worker_class> get_all_worker(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_WORKER_INFO+" WHERE "+KEY_WORKER_CICLE_ID+"=? AND "+KEY_WORKER_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<worker_class> worker_list=new ArrayList<>();
        if(cursor!=null){
            Log.d("worker_list_size",String.valueOf(worker_list.size()));
            if(cursor.moveToFirst()){
                Log.d("db_worker_list_size",String.valueOf(cursor.getCount()));
                do{
                    worker_class worker=new worker_class();
                    worker.setWorker_id(cursor.getInt(0));
                    worker.setCicle_id(cursor.getInt(1));
                    worker.setBuilding_id(cursor.getInt(2));
                    worker.setName(cursor.getString(3));
                    worker.setAddress(cursor.getString(4));
                    worker.setTel(cursor.getString(5));
                    worker.setDate_start(cursor.getString(6));
                    worker.setPrice_per_day(cursor.getInt(7));
                    worker_list.add(worker);
                }while(cursor.moveToNext());
            }
        }
        return  worker_list;
    }



    //Animal
    public void createAnimal(animal_class animal){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_ANIMAL_CICLE_ID,animal.getCicle_id());
        values.put(KEY_ANIMAL_BUILDING_ID,animal.getBuilding_id());
        values.put(KEY_ANIMAL_TYPE,animal.getType());
        values.put(KEY_ANIMAL_OTHER,animal.getOther());
        values.put(KEY_ANIMAL_QUANTITY, animal.getQuantity());
        values.put(KEY_ANIMAL_PRICE,animal.getPrice());
        long row=0;
        row = db.insert(TABLE_ANIMAL_INFO,null,values);
        Log.d("create_animal_inserted",String.valueOf(row));
    }

    public animal_class getAnimal(int animal_id) {
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_ANIMAL_INFO+" WHERE "+KEY_ANIMAL_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(animal_id)});
        animal_class animal=new animal_class();
        if(cursor!=null){
            Log.d("db_animal_get",String.valueOf(cursor.getCount()));
            if(cursor.moveToFirst()){
                animal.setAnimal_id(cursor.getInt(0));
                animal.setCicle_id(cursor.getInt(1));
                animal.setBuilding_id(cursor.getInt(2));
                animal.setType(cursor.getString(3));
                animal.setOther(cursor.getString(4));
                animal.setQuantity(cursor.getInt(5));
                animal.setPrice(cursor.getInt(6));
            }
        }
        return animal;
    }

    public int deletAnimal(animal_class animal){
        SQLiteDatabase db=getWritableDatabase();
        int affected_row=0;
        affected_row=db.delete(TABLE_ANIMAL_INFO, KEY_ANIMAL_ID + "=?", new String[]{String.valueOf(animal.getAnimal_id())});
        db.close();
        return affected_row;
    }

    public List<animal_class> getAllAnimal(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM " + TABLE_ANIMAL_INFO+" WHERE "+KEY_ANIMAL_CICLE_ID + "=? AND " + KEY_ANIMAL_BUILDING_ID+"=?";
        Cursor cursor = db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<animal_class> animal_list=new ArrayList<>();
        if(cursor!=null){
            Log.d("db_animal_list_size",String.valueOf(animal_list.size()));
            if(cursor.moveToFirst()){
                do{
                    animal_class animal=new animal_class();
                    animal.setAnimal_id(cursor.getInt(0));
                    animal.setCicle_id(cursor.getInt(1));
                    animal.setBuilding_id(cursor.getInt(2));
                    animal.setType(cursor.getString(3));
                    animal.setOther(cursor.getString(4));
                    animal.setQuantity(cursor.getInt(5));
                    animal.setPrice(cursor.getInt(6));
                    animal_list.add(animal);
                }while(cursor.moveToNext());
            }
        }
        return  animal_list;
    }

    public int updateAnimal(animal_class animal){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_ANIMAL_ID,animal.getAnimal_id());
        values.put(KEY_ANIMAL_CICLE_ID,animal.getCicle_id());
        values.put(KEY_ANIMAL_BUILDING_ID,animal.getBuilding_id());
        values.put(KEY_ANIMAL_TYPE,animal.getType());
        values.put(KEY_ANIMAL_OTHER,animal.getOther());
        values.put(KEY_ANIMAL_QUANTITY,animal.getQuantity());
        values.put(KEY_ANIMAL_PRICE, animal.getPrice());
        int count=db.update(TABLE_ANIMAL_INFO,values,KEY_ANIMAL_ID+"=?",new String[]{String.valueOf(animal.getAnimal_id())});
        db.close();
        Log.d("db_animal_update_count", String.valueOf(count));
        return count;
    }

    public int getAnimalCount(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_ANIMAL_INFO+" WHERE "+KEY_ANIMAL_CICLE_ID+"=? AND "+KEY_ANIMAL_BUILDING_ID+"=?";
        int count=0;
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<animal_class> animal_list=new ArrayList<>();
        if(cursor!=null) {
            Log.d("db_animal_list_size", String.valueOf(animal_list.size()));
            count=cursor.getCount();
        }
        return count;
    }



    //Settings
    public void insert_setting(setting_class setting) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SETTING_NOTIFICATION,setting.getNotification());
        values.put(KEY_SETTING_COUNTRY,setting.getCountry());
        values.put(KEY_SETTING_MONEY,setting.getMoney_format());
        db.insert(TABLE_SETTING_INFO, null, values);
        Log.d("table_setting_insert", "inserted");
    }

    public setting_class get_all_setting(){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_SETTING_INFO;
        setting_class setting=null;
        Cursor cursor=db.rawQuery(sql, null);
        if(cursor!=null){
            Log.d("setting_get_all",String.valueOf(cursor.getCount()));
            if(cursor.moveToFirst()){
                setting=new setting_class();
                Log.d("setting_all",String.valueOf(cursor.getCount()));
                setting.setNotification(cursor.getString(0));
                setting.setCountry(cursor.getString(1));
                setting.setMoney_format(cursor.getString(2));
            }
        }

        return setting;
    }

    public int delete_setting(setting_class setting){
        int affected_rows=0;
        SQLiteDatabase db=getWritableDatabase();
        if(setting!=null) {
            affected_rows = db.delete(TABLE_SETTING_INFO, KEY_SETTING_NOTIFICATION + "=? AND " + KEY_SETTING_COUNTRY + "=? AND " + KEY_SETTING_MONEY + "=?", new String[]{setting.getNotification(), setting.getCountry(), setting.getMoney_format()});
        }
        Log.d("setting_del_affected",String.valueOf(affected_rows));

        return affected_rows;
    }

    public int countSetting(){
        int count=-2;
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_SETTING_INFO;
        Cursor cursor=db.rawQuery(sql, null);
        if(cursor!=null){
            count=cursor.getCount();
        }
        return count;
    }



    //Food
    public void createFood(food_class food){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_FOOD_CICLE_ID,food.getCicle_id());
        values.put(KEY_FOOD_BUILDING_ID,food.getBuilding_id());
        values.put(KEY_FOOD_DATE,food.getDate());
        values.put(KEY_FOOD_DESIGNATION,food.getDesignation());
        values.put(KEY_FOOD_QUANTITY, food.getQuantity());
        values.put(KEY_FOOD_PRICE,food.getPrice());
        long row=0;
        row=db.insert(TABLE_FOOD_INFO,null,values);
        Log.d("create_food_inserted", String.valueOf(row));
    }

    public food_class getFood(int food_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_FOOD_INFO+" WHERE "+KEY_FOOD_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(food_id)});
        food_class food=new food_class();
        if(cursor!=null){
            Log.d("db_animal_get",String.valueOf(cursor.getCount()));
            if(cursor.moveToFirst()){
                food.setFood_id(cursor.getInt(0));
                food.setCicle_id(cursor.getInt(1));
                food.setBuilding_id(cursor.getInt(2));
                food.setDate(cursor.getString(3));
                food.setDesignation(cursor.getString(4));
                food.setQuantity(cursor.getInt(5));
                food.setPrice(cursor.getInt(6));
            }
        }
        return food;
    }

    public int deleteFood(food_class food){
        SQLiteDatabase db=getWritableDatabase();
        int affected_row=0;
        affected_row=db.delete(TABLE_FOOD_INFO, KEY_FOOD_ID + "=?", new String[]{String.valueOf(food.getFood_id())});
        db.close();
        return affected_row;
    }

    public List<food_class> getAllFood(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_FOOD_INFO+" WHERE "+KEY_FOOD_CICLE_ID+"=? AND "+KEY_FOOD_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<food_class> food_list=new ArrayList<>();
        if(cursor!=null){
            Log.d("db_food_list_size",String.valueOf(food_list.size()));
            if(cursor.moveToFirst()){
                do{
                    food_class food=new food_class();
                    food.setFood_id(cursor.getInt(0));
                    food.setCicle_id(cursor.getInt(1));
                    food.setBuilding_id(cursor.getInt(2));
                    food.setDate(cursor.getString(3));
                    food.setDesignation(cursor.getString(4));
                    food.setQuantity(cursor.getInt(5));
                    food.setPrice(cursor.getInt(6));
                    food_list.add(food);
                }while(cursor.moveToNext());
            }
        }
        return  food_list;
    }

    public int updateFood(food_class food){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_FOOD_ID,food.getFood_id());
        values.put(KEY_FOOD_CICLE_ID,food.getCicle_id());
        values.put(KEY_FOOD_BUILDING_ID,food.getBuilding_id());
        values.put(KEY_FOOD_DATE,food.getDate());
        values.put(KEY_FOOD_DESIGNATION,food.getDesignation());
        values.put(KEY_FOOD_QUANTITY, food.getQuantity());
        values.put(KEY_FOOD_PRICE,food.getPrice());
        int count=db.update(TABLE_FOOD_INFO, values, KEY_FOOD_ID + "=?", new String[]{String.valueOf(food.getFood_id())});
        db.close();
        Log.d("db_animal_update_count", String.valueOf(count));
        return count;
    }

    public int getFoodCount(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_FOOD_INFO+" WHERE "+KEY_FOOD_CICLE_ID+"=? AND "+KEY_FOOD_BUILDING_ID+"=?";
        int count=0;
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<food_class> food_list=new ArrayList<>();
        if(cursor!=null) {
            Log.d("db_food_list_size", String.valueOf(food_list.size()));
            count=cursor.getCount();
        }
        return count;
    }



    //Medical
    public void createMedical(medical_ medical){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_MEDICAL_CICLE_ID,medical.getCicle_id());
        values.put(KEY_MEDICAL_BUILDING_ID,medical.getBuilding_id());
        values.put(KEY_MEDICAL_OPERATION_TYPE,medical.getOperation_type());
        values.put(KEY_MEDICAL_OPERATION_NAME,medical.getOperation_name());
        values.put(KEY_MEDICAL_DATE, medical.getDate());
        values.put(KEY_MEDICAL_COMMENT,medical.getComment());
        values.put(KEY_MEDICAL_PRODUCT,medical.getProduct());
        values.put(KEY_MEDICAL_PRICE, medical.getPrice());
        db.insert(TABLE_MEDICAL_INFO, null, values);
        Log.d("Medical_insert", "inserted");
    }

    public medical_ getMedical(int medical_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_MEDICAL_INFO+" WHERE "+KEY_MEDICAL_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(medical_id)});
        medical_ medical=new medical_();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                medical.setMedical_id(cursor.getInt(0));
                medical.setCicle_id(cursor.getInt(1));
                medical.setBuilding_id(cursor.getInt(2));
                medical.setOperation_type(cursor.getString(3));
                medical.setOperation_name(cursor.getString(4));
                medical.setDate(cursor.getString(5));
                medical.setComment(cursor.getString(6));
                medical.setProduct(cursor.getString(7));
                medical.setPrice(cursor.getInt(8));
            }
        }
        //Log.d("medical_get_medical", medical.getOperation_type());
        return medical;

    }

    public void deleteMedical(medical_ medical){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_MEDICAL_INFO, KEY_MEDICAL_ID + "=?", new String[]{String.valueOf(medical.getMedical_id())});
        Log.d("medical_del_status", "deleted");
        db.close();
    }

    public int updateMedical(medical_ medical){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_MEDICAL_CICLE_ID,medical.getCicle_id());
        values.put(KEY_MEDICAL_BUILDING_ID,medical.getBuilding_id());
        values.put(KEY_MEDICAL_OPERATION_TYPE, medical.getOperation_type());
        values.put(KEY_MEDICAL_OPERATION_NAME, medical.getOperation_name());
        values.put(KEY_MEDICAL_DATE, medical.getDate());
        values.put(KEY_MEDICAL_COMMENT, medical.getComment());
        values.put(KEY_MEDICAL_PRODUCT, medical.getProduct());
        values.put(KEY_MEDICAL_PRICE, medical.getPrice());
        int count=db.update(TABLE_MEDICAL_INFO,values,KEY_MEDICAL_ID+"=?",new String[]{String.valueOf(medical.getMedical_id())});
        Log.d("medical_update", String.valueOf(count));
        Log.d("medical_update_type", medical.getOperation_type());
        Log.d("medical_update_des", medical.getOperation_name());
        Log.d("medical_update_equidid", String.valueOf(medical.getMedical_id()));

        db.close();
        return count;
    }

    public List<medical_> get_all_medical(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_MEDICAL_INFO+" WHERE "+KEY_MEDICAL_CICLE_ID+"=? AND "+KEY_MEDICAL_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<medical_> medical_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    medical_ medical=new medical_();
                    medical.setMedical_id(cursor.getInt(0));
                    medical.setCicle_id(cursor.getInt(1));
                    medical.setBuilding_id(cursor.getInt(2));
                    medical.setOperation_type(cursor.getString(3));
                    medical.setOperation_name(cursor.getString(4));
                    medical.setDate(cursor.getString(5));
                    medical.setComment(cursor.getString(6));
                    medical.setProduct(cursor.getString(7));
                    medical.setPrice(cursor.getInt(8));
                    medical_list.add(medical);
                }while(cursor.moveToNext());
            }
        }
        Log.d("medical_list", String.valueOf(medical_list.size()));
        return  medical_list;
    }



    //Death
    public void createDeath(death_ death){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_DEATH_CICLE_ID, death.getCicle_id());
        values.put(KEY_DEATH_BUILDING_ID,death.getBuilding_id());
        values.put(KEY_DEATH_DATE,death.getDate());
        values.put(KEY_DEATH_NO,death.getDeath_no());
        db.insert(TABLE_DEATH_INFO, null, values);
        Log.d("equip_insert", "inserted");
    }

    public death_ getDeath(int death_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_DEATH_INFO+" WHERE "+KEY_DEATH_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(death_id)});
        death_ death=new death_();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                death.setDeath_id(cursor.getInt(0));
                death.setCicle_id(cursor.getInt(1));
                death.setBuilding_id(cursor.getInt(2));
                death.setDate(cursor.getString(3));
                death.setDeath_no(cursor.getInt(4));
            }
        }
        //Log.d("equipment_get_equipment", death.getType());
        return death;

    }

    public void deleteDeath(death_ death){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_DEATH_INFO, KEY_DEATH_ID + "=?", new String[]{String.valueOf(death.getDeath_id())});
        Log.d("death_del_status", "deleted");
        db.close();
    }

    public int updateDeath(death_ death){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_DEATH_CICLE_ID,death.getCicle_id());
        values.put(KEY_DEATH_BUILDING_ID,death.getBuilding_id());
        values.put(KEY_DEATH_DATE, death.getDate());
        values.put(KEY_DEATH_NO, death.getDeath_no());
        int count=db.update(TABLE_DEATH_INFO, values, KEY_DEATH_ID + "=?", new String[]{String.valueOf(death.getDeath_id())});
        Log.d("death_update", String.valueOf(count));
        Log.d("death_update_type", death.getDate());
        Log.d("death_update_deathid",String.valueOf(death.getDeath_id()));

        db.close();
        return count;
    }

    public List<death_> get_all_death(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_DEATH_INFO+" WHERE "+KEY_DEATH_CICLE_ID+"=? AND "+KEY_DEATH_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<death_> death_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    death_ death=new death_();
                    death.setDeath_id(cursor.getInt(0));
                    death.setCicle_id(cursor.getInt(1));
                    death.setBuilding_id(cursor.getInt(2));
                    death.setDate(cursor.getString(3));
                    death.setDeath_no(cursor.getInt(4));
                    death_list.add(death);
                }while(cursor.moveToNext());
            }
        }
        Log.d("death_list", String.valueOf(death_list.size()));
        return  death_list;
    }



    //EGG
    public void createEgg(egg_ egg){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_EGG_CICLE_ID, egg.getCicle_id());
        values.put(KEY_EGG_BUILDING_ID,egg.getBuilding_id());
        values.put(KEY_EGG_DATE,egg.getDate());
        values.put(KEY_EGG_NUMBER,egg.getNumber());
        values.put(KEY_EGG_TYPE,egg.getType());
        db.insert(TABLE_EGG_INFO, null, values);
        Log.d("equip_insert", "inserted");
    }

    public egg_ getEgg(int egg_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_EGG_INFO+" WHERE "+KEY_EGG_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(egg_id)});
        egg_ egg=new egg_();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                egg.setEgg_id(cursor.getInt(0));
                egg.setCicle_id(cursor.getInt(1));
                egg.setBuilding_id(cursor.getInt(2));
                egg.setDate(cursor.getString(3));
                egg.setNumber(cursor.getInt(4));
                egg.setType(cursor.getString(5));
            }
        }
        //Log.d("equipment_get_equipment", death.getType());
        return egg;

    }

    public void deleteEgg(egg_ egg){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_EGG_INFO, KEY_EGG_ID + "=?", new String[]{String.valueOf(egg.getEgg_id())});
        Log.d("egg_del_status", "deleted");
        db.close();
    }

    public int updateEgg(egg_ egg){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_EGG_CICLE_ID,egg.getCicle_id());
        values.put(KEY_EGG_BUILDING_ID,egg.getBuilding_id());
        values.put(KEY_EGG_DATE, egg.getDate());
        values.put(KEY_EGG_NUMBER, egg.getNumber());
        values.put(KEY_EGG_TYPE, egg.getType());
        int count=db.update(TABLE_EGG_INFO, values, KEY_EGG_ID + "=?", new String[]{String.valueOf(egg.getEgg_id())});
        Log.d("egg_update", String.valueOf(count));
        Log.d("egg_update_type", egg.getDate());
        Log.d("egg_update_eggid",String.valueOf(egg.getEgg_id()));

        db.close();
        return count;
    }

    public List<egg_> get_all_egg(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_EGG_INFO+" WHERE "+KEY_EGG_CICLE_ID+"=? AND "+KEY_EGG_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<egg_> egg_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    egg_ egg=new egg_();
                    egg.setEgg_id(cursor.getInt(0));
                    egg.setCicle_id(cursor.getInt(1));
                    egg.setBuilding_id(cursor.getInt(2));
                    egg.setDate(cursor.getString(3));
                    egg.setNumber(cursor.getInt(4));
                    egg.setType(cursor.getString(5));
                    egg_list.add(egg);
                }while(cursor.moveToNext());
            }
        }
        Log.d("egg_list", String.valueOf(egg_list.size()));
        return  egg_list;
    }



    //Alive
    public int count_animals(int cicle_id, int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT "+KEY_ANIMAL_QUANTITY+" FROM "+TABLE_ANIMAL_INFO+" WHERE "+KEY_ANIMAL_CICLE_ID+"=? AND "+KEY_ANIMAL_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        int count = 0;
        if(cursor!=null){
            if(cursor.moveToFirst())
                count = cursor.getInt(0);
        }
        Log.d("count of animal", String.valueOf(count));
        return count;
    }

    public int count_deaths(int cicle_id, int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_DEATH_INFO+" WHERE "+KEY_DEATH_CICLE_ID+"=? AND "+KEY_DEATH_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        int count = 0;
        if(cursor!=null){
            while(cursor.moveToNext()){
                count += cursor.getInt(4);
            }
        }
        return count;
    }

}
