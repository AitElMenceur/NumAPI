package com.example.projetandroid.presentation.model;

public class Fact {
    private String Fact;
    private boolean found;
    private int number;

    /**
     * Getter for fact
     *
     * @return Fact
     */
    public String getFact() {
        return Fact;
    }

    /**
     * Getter for found

     * @return found
     */
    public boolean isFound() {
        return found;
    }

    /**
     * Getter for number
     * @return number
     */
    public int getNumber() {
        return number;
    }


    public Fact(String fact, int number, boolean found) {
        Fact = fact;
        this.found = found;
        this.number = number;
    }
}
