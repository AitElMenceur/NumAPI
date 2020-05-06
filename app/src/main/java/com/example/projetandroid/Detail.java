package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Detail extends AppCompatActivity {
    static final String BASE_URL = "https://www.alphavantage.co";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent secondIntent = getIntent();
        String title = secondIntent.getStringExtra("Title");
        String symbol = secondIntent.getStringExtra("Symbol");
        TextView Title = findViewById(R.id.Title);

        Title.setText(title);
        APICall(symbol);
    }

    private void APICall(String symbol) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);

        Call<RestAlphaRespond> call = gerritAPI.getStock("TIME_SERIES_INTRADAY", symbol, "5min", "9JUYFJMS0BK2O37M");
        call.enqueue(new Callback<RestAlphaRespond>() {
            @Override
            public void onResponse(Call<RestAlphaRespond> call, Response<RestAlphaRespond> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MetaData metaData = response.body().getMetaData();
                    TimeSerial timeSerie = response.body().getTimeSerie();
                    TextView Last_Refreshed = findViewById(R.id.Last_Refreshed);
                    Last_Refreshed.setText(metaData.getLast_Refreshed());

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

