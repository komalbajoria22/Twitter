package com.komal.twitter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.komal.twitter.HomePackage.Home;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    Button login,signup;
    TextView username,password;
    ImageView logo;
    ConstraintLayout layout;

    public void logIn(View view){
           if(username.getText().toString().matches("") || password.getText().toString().matches("")){
               Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
           }
          else{
               ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                   @Override
                   public void done(ParseUser user, ParseException e) {
                       if(user!=null){
                           Intent i=new Intent(MainActivity.this, Home.class);
                            startActivity(i);
                            Toast.makeText(MainActivity.this,"Log In successfully",Toast.LENGTH_SHORT).show();
                       }

                       else{
                          Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                       }
                   }
               });


           }


    }



    public void signUp(View view){
        if(username.getText().toString().matches("") || password.getText().toString().matches("")){
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
        }
        else{
           ParseUser user=new ParseUser();
           user.setUsername(username.getText().toString());
           user.setPassword(password.getText().toString());

           user.signUpInBackground(new SignUpCallback() {
               @Override
               public void done(ParseException e) {
                   if(e==null){
                       Intent i=new Intent(MainActivity.this,Home.class);
                       startActivity(i);
                       Toast.makeText(MainActivity.this,"Sign Up successfully",Toast.LENGTH_SHORT).show();

                   }
                   else{
                       Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                   }

               }
           });

        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=(Button)findViewById(R.id.login);
        signup=(Button)findViewById(R.id.signup);
        username=(TextView)findViewById(R.id.username);
        password=(TextView)findViewById(R.id.password);
        logo=(ImageView)findViewById(R.id.logo);
        layout=(ConstraintLayout)findViewById(R.id.layout);



    }
}