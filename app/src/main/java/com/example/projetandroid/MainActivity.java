package com.example.projetandroid;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(Constant.FACT_PREF, Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        refreshLayout = findViewById(R.id.swiperefresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Resfreshdata();
            }
        });
        ShowList();
    }

    private void ShowList() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            input.add(Integer.toString(i));
        }
        mAdapter = new ListAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }


    private void Resfreshdata() {
        refreshLayout.setRefreshing(true);
        saveAllFact();
        refreshLayout.setRefreshing(false);
    }


    private void APICall(String symbol) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);

        Call<RestNumbersAPI> call = gerritAPI.GetJson(symbol, "true");
        call.enqueue(new Callback<RestNumbersAPI>() {
            @Override
            public void onResponse(Call<RestNumbersAPI> call, Response<RestNumbersAPI> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String Fact = response.body().getFact();
                    saveaFact(symbol, Fact);
                } else {
                    Toast.makeText(getApplicationContext(), "Offline", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RestNumbersAPI> call, Throwable t) {
                showAPIError();
            }

        });

    }

    private void saveaFact(String symbol, String fact) {
        int number = Integer.parseInt(symbol);
        sharedPreferences
                .edit()
                .putString("cle_string" + number, fact)
                .apply();
    }


    private void saveAllFact() {
        for (int i = 0; i < 200; i++) {
            APICall(Integer.toString(i));
        }
    }
    private void showAPIError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_LONG).show();
    }
}
