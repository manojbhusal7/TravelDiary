
package com.example.traveldiary.View.Utilities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.traveldiary.R;

public class Firstcalendar extends AppCompatActivity {
    private EditText text;
    private ImageView image;

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diary);
        text = findViewById(R.id.EditText2);
        image = findViewById(R.id.imageView2);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
openDialog();
            }
        });

    }
    private void openDialog(){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                text.setText(String.valueOf(year)+"."+String.valueOf(month+1)+"."+String.valueOf(dayOfMonth));
            }
        }, 2022, 0, 15);
        dialog.show();
    }
}
