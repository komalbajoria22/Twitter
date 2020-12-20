package com.komal.twitter.HomePackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.komal.twitter.R;
import com.parse.ParseUser;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

  private HomeListData[] listData;

    public HomeAdapter(HomeListData[] listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.home_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {

        final HomeListData homeListData = listData[position];
        holder.following_name.setText(listData[position].getFollowing_name());
        holder.following_tweet.setText(listData[position].getFollowing_tweet());
        holder.name.setText(listData[position].getName());

    }



    @Override
    public int getItemCount() {
        return listData.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView following_name,following_tweet,name;
        public ViewHolder(@NonNull View itemView) {

                super(itemView);
                this.following_name = (TextView) itemView.findViewById(R.id.following_name);
                this.following_tweet = (TextView) itemView.findViewById(R.id.following_tweet);
                this.name=(TextView)itemView.findViewById(R.id.name);


            }

        }


    }



