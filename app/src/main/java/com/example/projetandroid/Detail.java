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
    static final String BASE_URL = "https://numbersapi.p.rapidapi.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent secondIntent = getIntent();
        String symbol = secondIntent.getStringExtra("Number");
        TextView Title = findViewById(R.id.Title);

        Title.setText(symbol);
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

        Call<RestNumbersAPI> call = gerritAPI.GetJson(symbol, "true");
        call.enqueue(new Callback<RestNumbersAPI>() {
            @Override
            public void onResponse(Call<RestNumbersAPI> call, Response<RestNumbersAPI> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String Fact = response.body().getFact();
                    Toast.makeText(getApplicationContext(), Fact, Toast.LENGTH_LONG).show();
                    TextView Txt_Fact = findViewById(R.id.Txt_Fact);
                    Txt_Fact.setText(Fact);
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

    private void showAPIError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_LONG).show();
    }
}

