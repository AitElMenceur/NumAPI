package com.example.projetandroid.presentation.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.projetandroid.presentation.Singletons;
import com.example.projetandroid.presentation.model.Fact;
import com.example.projetandroid.presentation.model.RestNumbersAPI;
import com.example.projetandroid.presentation.view.Detail;
import com.example.projetandroid.presentation.view.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void onItemClick(String position) {
        Intent intent = new Intent(view.getApplicationContext(), Detail.class);
        intent.putExtra("Number", position);
        view.startActivity(intent);

    }

    /**
     * API call which save a fact in the local cache
     *
     * @param symbol
     */
    private void APICall(String symbol) {
        Call<RestNumbersAPI> call = Singletons.getgerritAPIInstance().GetJson(symbol, "true");
        call.enqueue(new Callback<RestNumbersAPI>() {
            @Override
            public void onResponse(@NonNull Call<RestNumbersAPI> call, @NonNull Response<RestNumbersAPI> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Fact fact = new Fact(response.body().getFact(), response.body().getNumber(), response.body().isFound());
                    saveaFact(symbol, fact);
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

    /**
     * store a fact
     * @param symbol
     * @param fact
     */
    private void saveaFact(String symbol, Fact fact) {
        int number = Integer.parseInt(symbol);
        sharedPreferences
                .edit()
                .putString("cle_string" + number, Singletons.getGsonInstance().toJson(fact))
                .apply();
    }

    /**
     * Store all facts
     */
    public void saveAllFact() {
        for (int i = 0; i < 200; i++) {
            APICall(Integer.toString(i));
        }
    }

}


