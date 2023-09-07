package com.example.traveldiary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveldiary.Model.FollowModel;
import com.example.traveldiary.Model.SearchModel;
import com.example.traveldiary.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SuggestionListAdapter  extends RecyclerView.Adapter<SuggestionListAdapter.SuggestionListViewHolder>{
    Context context;
    ArrayList<FollowModel> arrFollow;
    public SuggestionListAdapter(Context mContext, ArrayList<FollowModel> arrFollow){
        context = mContext;

    }

    @NonNull
    @Override
    public SuggestionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.followers,parent,false);
        SuggestionListViewHolder suggestionListViewHolder = new SuggestionListViewHolder(itemView);
        return suggestionListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionListViewHolder holder, int position) {
        holder.tvName.setText("Shambhu Chaudhary");
        holder.tvAddress.setText(("Danda,Nawalpur"));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class SuggestionListViewHolder extends RecyclerView.ViewHolder{
ImageView ivImg;
TextView tvName;
TextView tvAddress;
Button btfollow;


        public SuggestionListViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImg =itemView.findViewById(R.id.ivImg);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            btfollow= itemView.findViewById(R.id.btfollow);

        }
    }

}
