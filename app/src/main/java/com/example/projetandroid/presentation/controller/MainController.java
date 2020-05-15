package com.example.projetandroid.presentation.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.projetandroid.Constant;
import com.example.projetandroid.data.GerritAPI;
import com.example.projetandroid.presentation.model.RestNumbersAPI;
import com.example.projetandroid.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {
    private SharedPreferences sharedPreferences;
    private MainActivity view;

    public MainController(MainActivity mainActivity, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart() {
        view.ShowList();
    }

    public void onItemClick() {

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
                    Toast.makeText(view, "Offline", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RestNumbersAPI> call, Throwable t) {
                view.showAPIError();
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


    public void saveAllFact() {
        for (int i = 0; i < 200; i++) {
            APICall(Integer.toString(i));
        }
    }

}


