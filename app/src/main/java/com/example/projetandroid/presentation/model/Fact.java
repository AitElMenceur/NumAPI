package com.example.projetandroid.presentation.model;

public class Fact {
    private String Fact;
    private boolean found;
    private int number;

    public String getFact() {
        return Fact;
    }

    public boolean isFound() {
        return found;
    }

    public int getNumber() {
        return number;
    }


    public Fact(String fact, int number, boolean found) {
        Fact = fact;
        this.found = found;
        this.number = number;
    }
}
