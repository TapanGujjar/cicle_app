package com.example.sneh.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CameraPictureActivity extends AppCompatActivity {


        Button addImage;
        ArrayList<Contact> imageArry = new ArrayList<Contact>();
        ContactImageAdapter imageAdapter;
        private static final int CAMERA_REQUEST = 1;
        FloatingActionButton fab;
        ListView dataList;
        byte[] imageName;
        int imageId;
        Bitmap theImage;
        DataBaseHandler db;

    public String getUser_description() {
        return user_description;
    }

    public void setUser_description(String user_description) {
        this.user_description = user_description;
    }

    String user_description;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_camera_picture);
            dataList = (ListView) findViewById(R.id.list_photos);
            /**
             * create DatabaseHandler object
             */
            Bundle bundle=new Bundle();
            if(bundle!=null) {
                user_description = bundle.getString("hello");
                if(user_description!=null)
                Log.d("receving", user_description);
            }



            db = new DataBaseHandler(this);
            /**
             * Reading and getting all records from database
             */
            List<Contact> contacts = db.getAllContacts();
            for (Contact cn : contacts) {
                String log = "ID:" + cn.getID() + " Name: " + cn.getName()
                        + " ,Image: " + cn.getImage();

                // Writing Contacts to log
                Log.d("Result: ", log);
                // add contacts data in arrayList
                imageArry.add(cn);

            }
            /**
             * Set Data base Item into listview
             */
            imageAdapter = new ContactImageAdapter(this, R.layout.screen_list,
                    imageArry);
            dataList.setAdapter(imageAdapter);

            /**
             * open dialog for choose camera
             */
            LayoutInflater li=LayoutInflater.from(this);

            View promptview=li.inflate(R.layout.prompts,null);

            final String[] option = new String[] {"Take from Camera"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                            android.R.layout.select_dialog_item, option);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    builder.setTitle("Select Option");
                    builder.setAdapter(adapter, new DialogInterface.OnClickListener() {




                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            Log.e("Selected Item", String.valueOf(which));
                            if (which == 0) {
                                callCamera();
                            }


                        }
                    });



                    final AlertDialog dialog = builder.create();

                    fab = (FloatingActionButton)findViewById(R.id.photo_button);

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    CameraPictureActivity.this);




                    fab.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            LayoutInflater layoutInflater = LayoutInflater.from(CameraPictureActivity.this);
                            final View promptView = layoutInflater.inflate(R.layout.prompts, null);
                            final EditText editText = (EditText) promptView.findViewById(R.id.editTextDialogUserInput);
                            alertDialogBuilder.setView(promptView);
                            alertDialogBuilder.setTitle("Enter Description");

                            alertDialogBuilder.setCancelable(false)
                                    .setPositiveButton("OK ", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                           setUser_description(editText.getText().toString());
                                            Log.d("Hello",user_description);
                                        }
                                    })

                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                                    AlertDialog alert=alertDialogBuilder.create();
                            dialog.show();
                            alert.show();








                }
            });

        }

        /**
         * On activity result
         */
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode != RESULT_OK)
                return;

            switch (requestCode) {
                case CAMERA_REQUEST:

                    Bundle extras = data.getExtras();

                    if (extras != null) {
                        Bitmap yourImage = extras.getParcelable("data");
                        // convert bitmap to byte
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte imageInByte[] = stream.toByteArray();
                        String g=data.getStringExtra("hel12");
                        String h=data.getStringExtra("hel123");
                        // Inserting Contacts
                        Log.d("Insert: ", "Inserting .."+getUser_description()+imageInByte);
                        Log.d("Insert: ", "Inserting .."+g+imageInByte+h);
                        if(imageInByte!=null)
                            db.addContact(new Contact(getUser_description(), imageInByte));
                        //getBaseContext().getContentResolver().delete(data.getData(), null, null);//to delete from gallary
                        Intent i = new Intent(CameraPictureActivity.this, CameraPictureActivity.class);

                        startActivity(i);
                        finish();

                    }
                    break;
            }
        }

        /**
         * open camera method
         */
        public void callCamera()
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Bundle bundle=new Bundle();
            bundle.putString("hello", user_description);
            intent.putExtra("hel12", user_description);
            Log.d("Camera", user_description);
            startActivityForResult(intent, CAMERA_REQUEST);

            intent.setType("image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("hel123", user_description);
            intent.putExtra("aspectX", 0);
            intent.putExtra("aspectY", 0);
            intent.putExtra("outputX", 250);
            intent.putExtra("outputY", 200);

        }
    }
