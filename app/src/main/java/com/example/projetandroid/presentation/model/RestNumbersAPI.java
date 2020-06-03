package com.example.projetandroid.presentation.model;


import com.google.gson.annotations.*;

public class RestNumbersAPI {

    @SerializedName("text")
    private String Fact;
    @SerializedName("number")
    private int number;
    @SerializedName("found")
    private boolean found;

    /**
     * Getter for number
     *
     * @return Fact
     */
    public String getFact() {
        return Fact;
    }

    /**
     * Getter for fact
     * @return Found
     */
    public boolean isFound() {
        return found;
    }

    /**
     * Getter for found
     * @return number
     */
    public int getNumber() {
        return number;
    }
}
