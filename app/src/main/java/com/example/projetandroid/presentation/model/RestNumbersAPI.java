package com.example.projetandroid.presentation.model;


import com.google.gson.annotations.*;

public class RestNumbersAPI {

    @SerializedName("text")
    private String Fact;
    @SerializedName("number")
    private int number;
    @SerializedName("found")
    private boolean found;


    public String getFact() {
        return Fact;
    }

    public boolean isFound() {
        return found;
    }

    public int getNumber() {
        return number;
    }
}
