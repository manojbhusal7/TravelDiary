package com.example.traveldiary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveldiary.Model.SearchModel;
import com.example.traveldiary.R;

import java.util.ArrayList;

public class RecyclerSearchAdapter extends RecyclerView.Adapter<RecyclerSearchAdapter.viewHolder> {
    Context context;
    ArrayList<SearchModel> arrSearch;


public RecyclerSearchAdapter(Context context, ArrayList<SearchModel> arrSearch){
    this.context=context;
this.arrSearch=arrSearch;
}
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.search_row, parent, false);
        viewHolder viewHolder = new viewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
holder.image.setImageResource(arrSearch.get(position).image);
        holder.tvImageContent.setText(arrSearch.get(position).placeName);
    }

    @Override
    public int getItemCount() {

    return arrSearch.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
    TextView tvImageContent;
    ImageView image;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvImageContent = itemView.findViewById(R.id.tvImageContent);
            image= itemView.findViewById(R.id.image);
        }
    }
}
