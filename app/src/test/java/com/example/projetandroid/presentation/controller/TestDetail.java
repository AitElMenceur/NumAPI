package com.example.projetandroid.presentation.controller;

import com.example.projetandroid.presentation.Singletons;
import com.example.projetandroid.presentation.model.Fact;
import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestDetail {
    Gson gson = Singletons.getGsonInstance();


    @Test
    public void testGetAFact() {

        Fact f = Singletons.getGsonInstance().fromJson(MockSharedPref.getString(), Fact.class);
        Fact expected = new Fact("a", 2, true);

        assertEquals(f.getFact(), expected.getFact());
        assertEquals(f.getNumber(), expected.getNumber());
        assertEquals(f.isFound(), expected.isFound());
    }

}