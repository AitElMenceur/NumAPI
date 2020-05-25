package com.example.projetandroid.presentation.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.projetandroid.presentation.Singletons;
import com.example.projetandroid.presentation.model.Fact;
import com.example.projetandroid.presentation.model.RestNumbersAPI;
import com.example.projetandroid.presentation.view.Detail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailController {
    private SharedPreferences sharedPreferences;
    private Detail view;

    public DetailController(Detail detail, SharedPreferences sharedPreferences) {
        this.view = detail;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart() {
        Intent secondIntent = view.getIntent();
        String symbol = secondIntent.getStringExtra("Number");
        view.showFact(symbol);
    }

    public void onRefresh() {
        Intent secondIntent = view.getIntent();
        String symbol = secondIntent.getStringExtra("Number");
        getNewFact(symbol);


    }

    public Fact getAFact(String symbol) {
        return Singletons.getGsonInstance().fromJson(sharedPreferences.getString("cle_string" + symbol, null), Fact.class);
    }

    private void getNewFact(String symbol) {

        Call<RestNumbersAPI> call = Singletons.getgerritAPIInstance().GetJson(symbol, "true");
        call.enqueue(new Callback<RestNumbersAPI>() {
            @Override
            public void onResponse(@NonNull Call<RestNumbersAPI> call, @NonNull Response<RestNumbersAPI> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Fact fact = new Fact(response.body().getFact(), response.body().getNumber(), response.body().isFound());
                    view.showNewFact(symbol, fact);
                } else {
                    Toast.makeText(view, "could'nt find " + symbol, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<RestNumbersAPI> call, @NonNull Throwable t) {
                view.showAPIError();
            }
        });
    }

}
