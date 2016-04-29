package com.example.sneh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText login_email_id,login_password;
    EditText register_fname,register_password,register_password_re,register_email,register_lname;
    Button login_button,register_button;
    db_handler handler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email_id=(EditText)findViewById(R.id.login_email_id);
        login_password=(EditText)findViewById(R.id.login_password);
        login_button = (Button) findViewById(R.id.login_button);
        handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        register_fname=(EditText)findViewById(R.id.register_firstname);
        register_lname=(EditText)findViewById(R.id.register_lastname);
        register_password=(EditText)findViewById(R.id.register_password);
        register_password_re=(EditText)findViewById(R.id.register_password_re);
        register_email=(EditText)findViewById(R.id.register_email);
        register_button=(Button)findViewById(R.id.register_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_id=login_email_id.getText().toString();
                String password=login_password.getText().toString();
                if(email_id.isEmpty() || password.isEmpty()){
                    Toast.makeText(Login.this, "Username or Password is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    int success=handler.check_user_exists(email_id.trim(),password.trim());
                    String message;
                    if(success==-1){
                        message="username and Password doesnot match/exists";

                    }
                    else if(success==1){
                        message="Success!";
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),Tasks.class);
                        startActivity(intent);

                    }
                    else
                        message="Internal DB error";

                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                }
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname,lname,password,password_re,email;
                fname=register_fname.getText().toString();
                lname=register_lname.getText().toString();
                password=register_password.getText().toString();
                password_re=register_password_re.getText().toString();
                email=register_email.getText().toString();

                if(fname.isEmpty() || lname.isEmpty() || password.isEmpty() || email.isEmpty()){
                    Toast.makeText(Login.this, "Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else if(password.equals(password_re)==false && password!=""){
                    Toast.makeText(getApplicationContext(),"Passwords doesnot match",Toast.LENGTH_SHORT).show();
                }
                else{
                    String message=handler.InsertUser(fname,lname,password,email);

                    if(message.equals("Success")){
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),Tasks.class);
                        startActivity(intent);
                    }
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

}
