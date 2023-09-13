package com.example.traveldiary.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.traveldiary.R;
import com.example.traveldiary.View.Pages.HomeActivity;
import com.example.traveldiary.remote.Diary;
import com.example.traveldiary.remote.User;

import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.HomePageListViewHolder> {
//    Context context;
    List<Diary> arrHome;
//    public  HomePageAdapter(Context mContext, List<HomePageModel> arrHome){
////        context = mContext;
//        this.arrHome=arrHome;
//
//    }
    public  HomePageAdapter(HomeActivity homeActivity, List<Diary> arrHome){
       this.arrHome = arrHome;

    }

    @NonNull
    @Override
    public HomePageListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_card,parent,false);
        HomePageListViewHolder homeListViewHolder = new HomePageListViewHolder(itemView);
        return homeListViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull HomePageListViewHolder holder, int position) {

        Diary homepagemodel = arrHome.get(position);
        User user = homepagemodel.getUser();

//        Log.d("Profile ma k aaxa","homepagemodel");
        holder.tvDiaryTitle.setText(homepagemodel.getTitle());
        Glide.with(holder.imgBg.getContext())
                .load(homepagemodel.getImages())
                .dontAnimate()
                .into(holder.imgBg);
        String fullname = user.getFullname();

        holder.tvName.setText(fullname);
        String photoUrl = user.getPhoto();
        Glide.with(holder.ivImg.getContext())
                .load(photoUrl)
                .placeholder(R.drawable.baseline_person_24)
                .dontAnimate()
                .into(holder.ivImg);




    }

    @Override
    public int getItemCount() {
        return arrHome.size();
    }

    public class HomePageListViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImg;
        TextView tvName;
        TextView tvDiaryTitle;

        ImageView imgBg;
//        ImageButton likeButton, commentButton, shareButton;


        public HomePageListViewHolder(@NonNull View itemView) {
            super(itemView);


            ivImg = itemView.findViewById(R.id.ivImg);
            tvName = itemView.findViewById(R.id.tvName);
            tvDiaryTitle = itemView.findViewById(R.id.tvDiaryTitle);
            imgBg=itemView.findViewById(R.id.imgBg);
//            likeButton = itemView.findViewById(R.id.likeButton);
//            commentButton = itemView.findViewById(R.id.commentButton);
//            shareButton = itemView.findViewById(R.id.shareButton);
       }
    }
}