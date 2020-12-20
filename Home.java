package com.komal.twitter.HomePackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.komal.twitter.Add;
import com.komal.twitter.Profile;
import com.komal.twitter.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.profile,menu);
        menuInflater.inflate(R.menu.add_tweet,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.profile){
            Intent intent=new Intent(this, Profile.class);
            startActivity(intent);
        }

        if(item.getItemId()==R.id.add_tweet){
            Intent intent=new Intent(this, Add.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       setTitle("Home");

        List alist=ParseUser.getCurrentUser().getList("isFollowing");
        String names=ParseUser.getCurrentUser().getUsername();


        HomeListData[] listData=new HomeListData[]{

          new HomeListData("komal","komal here",names),

        };



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        HomeAdapter adapter = new HomeAdapter(listData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



    }
}