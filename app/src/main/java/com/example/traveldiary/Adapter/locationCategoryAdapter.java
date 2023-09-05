package com.example.traveldiary.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveldiary.Model.LocationCategoryModel;
import com.example.traveldiary.R;

import java.util.List;

public class locationCategoryAdapter extends RecyclerView.Adapter<locationCategoryAdapter.LocationCHolder> {

    private List<LocationCategoryModel> LocationList;

    public locationCategoryAdapter(List<LocationCategoryModel> LocationList){
        this.LocationList = LocationList;
    }
    @NonNull
    @Override
    public LocationCHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_row, parent, false);
        return new LocationCHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationCHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return LocationList.size();
    }

    public class LocationCHolder extends RecyclerView.ViewHolder{

        private TextView tvLocation;
        private ImageView imgLocation;
        public LocationCHolder(View itemView) {
            super(itemView);
            tvLocation = itemView.findViewById(R.id.tvImageContent);
            imgLocation = itemView.findViewById(R.id.image);

        }
    }
}
