package com.example.traveldiary;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class userAdapter extends RecyclerView.Adapter<userAdapter.userHolder>{
     MainActivity mainActivity;
     List<userModel> allUserList;
     public userAdapter(MainActivity mainActivity, List<userModel> allUserList) {
this.mainActivity=mainActivity;
this.allUserList=allUserList;
     }

     @NonNull
     @Override
     public userHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          return new userHolder(LayoutInflater.from(mainActivity).inflate(R.layout.user_item, parent,false)) ;
     }

     @Override
     public void onBindViewHolder(@NonNull userHolder holder, int position) {
holder.itemTxt.setText(allUserList.get(position).getTitle());
     }

     @Override
     public int getItemCount() {
          return allUserList.size();
     }

     class userHolder extends RecyclerView.ViewHolder {
TextView  itemTxt;
          public userHolder(@NonNull View itemView) {
               super(itemView);
               itemTxt=itemView.findViewById(R.id.itemTxt);
          }
     }

}
