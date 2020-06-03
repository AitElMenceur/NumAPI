package com.example.projetandroid.presentation.controller;

import com.example.projetandroid.presentation.Singletons;
import com.example.projetandroid.presentation.model.Fact;
import com.google.gson.Gson;

public class MockSharedPref {

    public static String getString() {
        Gson gson = Singletons.getGsonInstance();
        return gson.toJson(new Fact("a", 2, true));
    }
}
