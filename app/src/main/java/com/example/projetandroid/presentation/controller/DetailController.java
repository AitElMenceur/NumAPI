package com.example.projetandroid.presentation.controller;

import android.content.Intent;
import android.content.SharedPreferences;

import com.example.projetandroid.presentation.Singletons;
import com.example.projetandroid.presentation.model.Fact;
import com.example.projetandroid.presentation.view.Detail;


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

    public Fact getAFact(String symbol) {
        return Singletons.getGsonInstance().fromJson(sharedPreferences.getString("cle_string" + symbol, null), Fact.class);
    }
}
