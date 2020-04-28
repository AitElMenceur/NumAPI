package com.example.projetandroid;
/*

API KEY: 9JUYFJMS0BK2O37M

 */

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout refreshLayout;
    static final String BASE_URL = "https://www.alphavantage.co";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = findViewById(R.id.swiperefresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Resfreshdata();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();
        for (Compagny c : Compagny.values()) {
            input.add(c.getName());
        }
        mAdapter = new ListAdapter(input);
        recyclerView.setAdapter(mAdapter);
        APICall();
    }

    private void Resfreshdata() {
        Toast.makeText(getApplicationContext(), "refresh", Toast.LENGTH_LONG).show();
        refreshLayout.setRefreshing(false);
    }


    private void APICall() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);

        Call<RestAlphaRespond> call = gerritAPI.getStock("TIME_SERIES_INTRADAY", "IBM", "5min", "9JUYFJMS0BK2O37M");
        call.enqueue(new Callback<RestAlphaRespond>() {
            @Override
            public void onResponse(Call<RestAlphaRespond> call, Response<RestAlphaRespond> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MetaData> metaDataList = response.body().getMetaDataList();
                    Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_LONG).show();
                } else {
                    showAPIError();
                }
            }

            @Override
            public void onFailure(Call<RestAlphaRespond> call, Throwable t) {
                showAPIError();
            }

        });

    }

    private void showAPIError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_LONG).show();
    }
}
