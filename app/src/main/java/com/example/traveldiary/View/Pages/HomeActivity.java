package com.example.traveldiary.View.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.traveldiary.Adapter.HomePageAdapter;
import com.example.traveldiary.Adapter.RecyclerSearchAdapter;
import com.example.traveldiary.Adapter.ViewPagerHomeAdapter;
import com.example.traveldiary.Model.HomePageModel;
import com.example.traveldiary.Model.SearchModel;
import com.example.traveldiary.R;
import com.example.traveldiary.View.Utilities.PopularFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    TabLayout tab;
    ViewPager ViewPager;
    RecyclerView rvDiaryPost ;
    ArrayList<HomePageModel> arrHome = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//         tab = findViewById(R.id.tab);
//
//      ViewPager = findViewById(R.id.ViewPager);
//
//
//        ViewPagerHomeAdapter adapter = new ViewPagerHomeAdapter(getSupportFragmentManager());
//        ViewPager.setAdapter(adapter);
//        tab.setupWithViewPager(ViewPager);




        rvDiaryPost=findViewById(R.id.rvDiaryPost);
        rvDiaryPost.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rvDiaryPost.setLayoutManager(linearLayoutManager);


        arrHome.add( new HomePageModel("R.drawable.pokhara","Shambhu Chaudhary","Danda") );
        HomePageAdapter adapter = new HomePageAdapter(this,arrHome);
        rvDiaryPost.setAdapter(adapter);


    }
}
