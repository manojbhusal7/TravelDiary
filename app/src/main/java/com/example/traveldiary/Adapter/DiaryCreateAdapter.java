package com.example.traveldiary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveldiary.R;
import com.example.traveldiary.remote.DiaryList;

import java.util.List;

public class DiaryCreateAdapter extends RecyclerView.Adapter<DiaryCreateAdapter.ViewHolder> {

    List<DiaryList> diaryList;
    Context context;

    public DiaryCreateAdapter(Context context, List<DiaryList> diaryList) {
        this.context = context;
        this.diaryList = diaryList;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_new_diary,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.EditText.setText(diaryList.get(position).getTitle());
        holder.EditText2.setText(diaryList.get(position).getStartDate());
        holder.EditText3.setText((CharSequence) diaryList.get(position).getEndDate());//Yo line ma setText garda error ayara asto gareko
        holder.EditText4.setText(diaryList.get(position).getLocation());
        holder.EditText5.setText(diaryList.get(position).getDescription());
//        holder.gallery.setImageResource();
//        holder.privacy_dropdown.setText(diaryList.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
 return diaryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        EditText EditText,EditText2,EditText3,EditText4,EditText5,EditText6;
        ImageView gallery;
        Spinner privacy_dropdown;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
EditText=itemView.findViewById(R.id.EditText);
            EditText=itemView.findViewById(R.id.EditText2);
            EditText=itemView.findViewById(R.id.EditText3);
            EditText=itemView.findViewById(R.id.EditText4);
            EditText=itemView.findViewById(R.id.EditText5);
            EditText=itemView.findViewById(R.id.EditText6);
            gallery=itemView.findViewById(R.id.gallery);
            privacy_dropdown=itemView.findViewById(R.id.privacy_dropdown);




        }
    }


    }
