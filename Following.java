package com.komal.twitter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Following extends AppCompatActivity {


    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter arrayAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);

        setTitle("Following");
        listView=(ListView)findViewById(R.id.listView);

        arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice,arrayList);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(arrayAdapter);

        if(ParseUser.getCurrentUser().get("isFollowing")==null){
            List<String> emptyList=new ArrayList<>();
            ParseUser.getCurrentUser().put("isFollowing",emptyList);
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(listView.isItemChecked(position)){

                    if(! ParseUser.getCurrentUser().getList("isFollowing").contains(arrayList.get(position))) {
                        ParseUser.getCurrentUser().getList("isFollowing").add(arrayList.get(position));
                        ParseUser.getCurrentUser().put("isFollowing",arrayList);
                        ParseUser.getCurrentUser().saveInBackground();
                    }
                }
                else {
                    if(ParseUser.getCurrentUser().getList("isFollowing").contains(arrayList.get(position))) {
                        List alist=ParseUser.getCurrentUser().getList("isFollowing");
                        alist.remove(arrayList.get(position));
                        ParseUser.getCurrentUser().put("isFollowing", alist);
                        ParseUser.getCurrentUser().saveInBackground();
                    }
                }
            }


        });

        arrayList.clear();

        ParseQuery<ParseUser> query=ParseUser.getQuery();
        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.orderByAscending("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null) {
                    if(objects.size()>0){
                        for (ParseUser users : objects) {
                            arrayList.add(users.getUsername());
                        }
                        arrayAdapter.notifyDataSetChanged();
                        for(String names:arrayList){
                            if(ParseUser.getCurrentUser().getList("isFollowing").contains(names)){
                                listView.setItemChecked(arrayList.indexOf(names),true);
                            }
                        }

                    }
                }

                else{
                    Toast.makeText(Following.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}