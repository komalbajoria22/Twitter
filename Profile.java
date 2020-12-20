package com.komal.twitter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarException;

public class Profile extends AppCompatActivity {

     TextView name;
     ListView profile_list;
     ArrayList<String> arrayList=new ArrayList<>();
     ArrayAdapter arrayAdapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.following,menu);
        menuInflater.inflate(R.menu.logout,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.logout){
            Intent i=new Intent(Profile.this, MainActivity.class);
            startActivity(i);

        }

        if(item.getItemId()==R.id.following){

            Intent i=new Intent(Profile.this, Following.class);
            startActivity(i);
        }

        if(item.getItemId()==R.id.followers){

            Intent i=new Intent(Profile.this, Followers.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setTitle("Profile");
        name=(TextView)findViewById(R.id.name);
        profile_list=(ListView)findViewById(R.id.profile_list);

       String user=ParseUser.getCurrentUser().getUsername();
         name.setText(user);

            arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
            profile_list.setAdapter(arrayAdapter);


        ParseQuery<ParseUser> query=ParseUser.getQuery();
        query.whereEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                   if(objects.size()>0){

                       for(ParseUser users:objects) {

                           JSONArray data =users.getJSONArray("Tweet");

                           for(int i=0;i<data.length();i++){

                               try {
                                   arrayList.add(data.getString(i));
                               } catch (JSONException jsonException) {
                                   jsonException.printStackTrace();
                               }


                           }


                       }
                       arrayAdapter.notifyDataSetChanged();
                }

            }
        }

        });

        arrayList.clear();

    }
}