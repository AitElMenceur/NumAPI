package com.example.projetandroid;


import com.google.gson.annotations.*;

public class RestNumbersAPI {

    @SerializedName("text")
    private String Fact;
    @SerializedName("found")
    private boolean found;
    @SerializedName("number")
    private String number;

    public String getFact() {
        return Fact;
    }

    public boolean isFound() {
        return found;
    }

    public String getNumber() {
        return number;
    }
}
