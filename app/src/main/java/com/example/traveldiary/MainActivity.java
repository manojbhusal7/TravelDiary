package com.example.traveldiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
 public static String api = "https://jsonplaceholder.typicode.com/";
 List<userModel> allUserList;
 RecyclerView rcvmain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvmain=findViewById(R.id.rcvmain);
        rcvmain.setLayoutManager(new LinearLayoutManager(this));

//RetrofitInstance.getRetrofitInstance().apiInterface.getUsers().enqueue(new Callback<List<userModel>>() {
//    @Override
//    public void onResponse(Call<List<userModel>> call, Response<List<userModel>> response) {
//        Log.e("api","onFailure:" +response.body().toString());
//        allUserList= response.body();
//        rcvmain.setAdapter(new userAdapter(MainActivity.this,allUserList));
//        for(int i=0; i<allUserList.size(); i++)
//        {
//            Log.e("api" ,"onResponse:" +allUserList.get(i).getTitle());
//        }
//
//        }
//
//
//    @Override
//    public void onFailure(Call<List<userModel>> call, Throwable t) {
//        Log.e("api","onFailure:" +t.getLocalizedMessage());
//    }
//});

    }
}