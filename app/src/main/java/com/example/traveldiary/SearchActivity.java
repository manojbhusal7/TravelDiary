package com.example.traveldiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.example.traveldiary.Adapter.SuggestionListAdapter;
import com.example.traveldiary.Model.FollowModel;
import com.example.traveldiary.Model.SearchModel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
SearchView searchView;
private RecyclerView rvSearch;
    RecyclerView rvFollorList;
ArrayList<SearchModel> arrSearch = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.Search);
        searchView.clearFocus();


        rvSearch=findViewById(R.id.rvSearch);
        rvSearch.setLayoutManager(new  LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        rvSearch.setLayoutManager(linearLayoutManager);

        arrSearch.add( new SearchModel(R.drawable.soyambhunath, "Soyambhunath, KTM") );
        arrSearch.add( new SearchModel(R.drawable.pokhara, "Pokhara, Kaski") );
        arrSearch.add( new SearchModel(R.drawable.pasupatinath, "Soyambhunath \n KTM") );
        RecyclerSearchAdapter adapter = new RecyclerSearchAdapter(this,arrSearch);
        rvSearch.setAdapter(adapter);


        ArrayList<FollowModel> arrFollow = new ArrayList<>();
        rvFollorList = findViewById(R.id.rvFollowList);
        arrFollow.add( new FollowModel(R.drawable.soyambhunath,"Manoj","Bharatpur" ) );
        arrFollow.add( new FollowModel(R.drawable.soyambhunath,"snjb","Bharatpur" ) );
        SuggestionListAdapter suggestionListAdapter = new SuggestionListAdapter(this,arrFollow);
        rvFollorList.setAdapter(suggestionListAdapter);
        rvFollorList.setLayoutManager(new LinearLayoutManager(this));
    }
}