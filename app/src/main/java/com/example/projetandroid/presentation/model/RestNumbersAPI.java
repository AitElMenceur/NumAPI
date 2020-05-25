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
     * @return
     */
    public String getFact() {
        return Fact;
    }

    /**
     * Getter for fact
     * @return
     */
    public boolean isFound() {
        return found;
    }

    /**
     * Getter for found
     * @return
     */
    public int getNumber() {
        return number;
    }
}
