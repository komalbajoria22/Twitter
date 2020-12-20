package com.komal.twitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class Add extends AppCompatActivity {

    EditText add;
    Button post;
    ArrayList<String> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        add=(EditText) findViewById(R.id.add);
        post=(Button)findViewById(R.id.post);

        if(ParseUser.getCurrentUser().get("Tweet")==null){
            List<String> emptyList=new ArrayList<>();
            ParseUser.getCurrentUser().put("Tweet",emptyList);
        }

       String text=add.getText().toString();

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text=add.getText().toString();

                ParseUser.getCurrentUser().getList("Tweet").add(text);
                ParseUser.getCurrentUser().put("Tweet",arrayList);
                ParseUser.getCurrentUser().saveInBackground();

                Intent i=new Intent(Add.this, Profile.class);
                startActivity(i);

            }
        });
        arrayList.clear();

    }
}