package com.example.traveldiary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveldiary.Model.HomePageModel;
import com.example.traveldiary.R;

import java.util.ArrayList;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.HomePageListViewHolder> {
    Context context;
    ArrayList<HomePageModel> arrHome;
    public  HomePageAdapter(Context mContext, ArrayList<HomePageModel> arrHome){
        context = mContext;
        this.arrHome=arrHome;

    }

    @NonNull
    @Override
    public HomePageListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.diary_card,parent,false);
        HomePageListViewHolder homeListViewHolder = new HomePageListViewHolder(itemView);
        return homeListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageListViewHolder holder, int position) {

        holder.tvName.setText("Shambhu Chaudhary");
        holder.tvAddress.setText("Danda,Nawalpur");
        holder.ivImg.setImageResource(R.drawable.lumbini);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class HomePageListViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImg;
        TextView tvName;
        TextView tvAddress;
        ImageButton likeButton, commentButton, shareButton;


        public HomePageListViewHolder(@NonNull View itemView) {
            super(itemView);


            ivImg = itemView.findViewById(R.id.ivImg);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
//            likeButton = itemView.findViewById(R.id.likeButton);
//            commentButton = itemView.findViewById(R.id.commentButton);
//            shareButton = itemView.findViewById(R.id.shareButton);
       }
    }
}