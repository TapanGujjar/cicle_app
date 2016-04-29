package com.example.sneh.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class AddCicle extends AppCompatActivity {
    ImageButton date_pick;
    int year_x, month_x, day_x;
    static final int DIALOG_ID = 999;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView date_text;
    ImageButton map_click;
    private TextView map_text;
    private boolean flag=false;
    private DatePickerDialog fromDatePickerDialog;
    String[] array_spinner;
    // AppLocationService appLocationService;
    private LocationManager locationMangaer=null;
    private LocationListener locationListener=null;





    //gps
    GPSTracker gps;
    Geocoder geocoder;
    double latitude;
    double longitude;
    String stringThisAddress;
    String City_state;
    Button add_cicle_save;
    EditText title,owner;
    Spinner spinner;
    int[] images;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cicle);
        title=(EditText)findViewById(R.id.add_cicle_title);
        owner=(EditText)findViewById(R.id.add_cicle_owner);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        array_spinner = new String[4];
        array_spinner[0] = "Type(Select)";
        array_spinner[1] = "type1";
        array_spinner[2] = "Type 2";
        array_spinner[3] = "Type 3";


        images= new int[]{0, R.drawable.simpleanimal, R.drawable.simpleanimal2, R.drawable.simpleanimal3};

        spinner = (Spinner) findViewById(R.id.add_cicle_type);
        // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        // spinner.setAdapter(adapter);
        spinner.setAdapter(new MyAdapter(AddCicle.this,R.layout.row,array_spinner));
        spinner.setSelection(0);
    /*
     int arr_images[] = { R.drawable.coderzheaven,
             R.drawable.google, R.drawable.microsoft,
             R.drawable.apple, R.drawable.yahoo, R.drawable.samsung};
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    Spinner mySpinner = (Spinner)findViewById(R.id.spinner);
    mySpinner.setAdapter(new MyAdapter(CustomSpinnerDemo.this, R.layout.row, strings));
  }
*/



        final db_handler db = new db_handler(getApplicationContext());
        db.onCreateTable(db.getWritableDatabase());


        add_cicle_save=(Button)findViewById(R.id.add_cicle_save);
        add_cicle_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _title=title.getText().toString();
                String _owner=owner.getText().toString();
                String _location=map_text.getText().toString();
                String _start_date=date_text.getText().toString();
                String _type=spinner.getSelectedItem().toString();

                Intent intent=new Intent(AddCicle.this, Tasks.class);

                final cicle cicle=new cicle();
                cicle.setTitle(_title);
                cicle.setOwner(_owner);
                cicle.setLocation(_location);
                cicle.setStart_date(_start_date);
                cicle.setType(_type);
                cicle.setDone(1);

                db.CreateCicle(cicle);

                startActivity(intent);
                finish();
            }
        });

    /*Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
    setSupportActionBar(myToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        // appLocationService = new AppLocationService(add_cicle.this);



        //setting date
        date_text = (TextView) findViewById(R.id.add_cicle_start_date);
        calendar=Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year_x, month_x + 1, day_x);



        //Setting gps
        map_text=(EditText)findViewById(R.id.add_cicle_location);
        map_click = (ImageButton) findViewById(R.id.imageButton2);
        locationMangaer=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        geocoder=new Geocoder(this);

        map_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(AddCicle.this);
                if(gps.canGetLocation()) {
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    // map_text.setText(latitude+"kk"+longitude);
                } else {
                    gps.showSettingsAlert();
                }
                if(latitude==0 && longitude==0)
                {
                    Toast.makeText(AddCicle.this, "Please Press the button again", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    List<Address> geoResult = findGeocoder(latitude, longitude);
                    if(geoResult != null){
                        // List<String> geoStringResult = new ArrayList<String>();
                        for(int i=0; i < geoResult.size(); i++){
                            Address thisAddress = geoResult.get(i);
                            stringThisAddress = "";
                            for(int a=0; a < thisAddress.getMaxAddressLineIndex(); a++) {
                                stringThisAddress += thisAddress.getAddressLine(a) + "\n";
                            }

                            stringThisAddress +=
                                    "CountryName: " + thisAddress.getCountryName() + "\n"
                                            + "CountryCode: " + thisAddress.getCountryCode() + "\n"
                                            + "AdminArea: " + thisAddress.getAdminArea() + "\n"
                                            + "FeatureName: " + thisAddress.getFeatureName();
                            // geoStringResult.add(stringThisAddress);
                            City_state=thisAddress.getAdminArea()+" ,"+thisAddress.getCountryName();
                        }
                    }

                }
                //System.out.print(stringThisAddress);
                // Log.d("9345",stringThisAddress);
                map_text.setText(City_state);
            }});


    }

    private List<Address> findGeocoder(Double lat, Double lon){
        final int maxResults = 2;
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat, lon, maxResults);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return addresses;
    }

    public class MyAdapter extends ArrayAdapter<String>{

        public MyAdapter(Context context, int textViewResourceId,  String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.row, parent, false);
            TextView label=(TextView)row.findViewById(R.id.company);
            label.setText(array_spinner[position]);


            ImageView icon=(ImageView)row.findViewById(R.id.image);
            if(position!=0) {
                icon.setImageResource(images[position]);
            }
            return row;
        }
    }

    @SuppressWarnings("deprecation")
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