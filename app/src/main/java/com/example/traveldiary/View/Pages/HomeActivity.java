package com.example.traveldiary.View.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.traveldiary.Adapter.HomePageAdapter;
import com.example.traveldiary.R;
import com.example.traveldiary.remote.APIUtil;
import com.example.traveldiary.remote.Diary;
import com.example.traveldiary.remote.DiaryResponse;
import com.example.traveldiary.remote.RegisterService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
//    TabLayout tab;
//    ViewPager ViewPager;
    RecyclerView rvDiaryPost ;
    List<Diary> arrHome;
    HomePageAdapter homePageAdapter;

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
//        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
//        rvDiaryPost.setLayoutManager(linearLayoutManager);
        Log.e("Parsejson maathi","json maathi");
        SharedPreferences shp = getSharedPreferences("USER", MODE_PRIVATE);
        String token = shp.getString("TOKEN", "0");

        if (token != null) {
            RegisterService registerService = APIUtil.getUserservices();
            Call<DiaryResponse> ApiResponseCall = registerService.diaries("Bearer " + token);
            ApiResponseCall.enqueue(new Callback<DiaryResponse>() {
                @Override
                public void onResponse(Call<DiaryResponse> call, Response<DiaryResponse> response) {
                    if (response.isSuccessful()) {
                        arrHome =response.body().getData();
                        homePageAdapter = new HomePageAdapter(HomeActivity.this,arrHome);
                        rvDiaryPost.setAdapter(homePageAdapter);
                        homePageAdapter.notifyDataSetChanged();


                    }
                    else {
                        Log.e("baseURL", "Response body is null");
                    }
                }

                @Override
                public void onFailure(Call<DiaryResponse> call, Throwable t) {
                    Log.e("HomeActivity", "API call failed with error: " + t.getMessage());
                }
            });
        }
        Log.d("HomeActivity", "Requesting diaries with token: " + token);

        Log.d("Parsejson tala","json tala");


       // arrHome.add( new HomePageModel("R.drawable.lumbini","Shambhu Chaudhary","Chitwan National Park","R.drawable.sauraha") );
//        HomePageAdapter adapter = new HomePageAdapter(this,arrHome);
//        rvDiaryPost.setAdapter(adapter);


    }
}
