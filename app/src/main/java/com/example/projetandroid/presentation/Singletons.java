package com.example.projetandroid.presentation;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.projetandroid.Constant;
import com.example.projetandroid.data.GerritAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {
    private static Gson gsonInstance;
    private static GerritAPI gerritAPIInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static GerritAPI getgerritAPIInstance() {
        if (gerritAPIInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGsonInstance()))
                    .build();
            return retrofit.create(GerritAPI.class);

        }
        return gerritAPIInstance;
    }

    public static Gson getGsonInstance() {
        if (gsonInstance == null) {
            return new GsonBuilder()
                    .setLenient()
                    .create();

        }
        return gsonInstance;
    }

    public static SharedPreferences getSharedPreferencesInstance(Context view) {
        if (sharedPreferencesInstance == null) {
            return view.getSharedPreferences(Constant.FACT_PREF, Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
