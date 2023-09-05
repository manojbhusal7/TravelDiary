package com.example.traveldiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {
    TabLayout tab;
    ViewPager ViewPager;
    RecyclerView recyclePopular ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
         tab = findViewById(R.id.tab);

      ViewPager = findViewById(R.id.ViewPager);


        ViewPagerHomeAdapter adapter = new ViewPagerHomeAdapter(getSupportFragmentManager());
        ViewPager.setAdapter(adapter);
        tab.setupWithViewPager(ViewPager);


        recyclePopular = findViewById(R.id.recyclePopular);
    }
}