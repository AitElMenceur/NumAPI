package com.example.projetandroid.presentation.controller;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.projetandroid.presentation.Singletons;
import com.example.projetandroid.presentation.model.Fact;
import com.example.projetandroid.presentation.model.RestNumbersAPI;
import com.example.projetandroid.presentation.view.MainActivity;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

public class MainControllerTest {
    private MainActivity mainActivity = new MainActivity();
    Context view = mainActivity.getBaseContext();

    @Test
    public void saveAFact() {

        Fact fact = new Fact("a", 1, true);
        MainController mainController = new MainController(mainActivity, Singletons.getSharedPreferencesInstance(mainActivity.getBaseContext()));
        mainController.saveaFact("1", fact);
        Fact f = Singletons.getGsonInstance().fromJson(Singletons.getSharedPreferencesInstance(mainActivity).getString("cle_string" + "1", null), Fact.class);
        assertEquals(f.getFact(), fact.getFact());
        assertEquals(f.getNumber(), fact.getNumber());
        assertEquals(f.isFound(), fact.isFound());
    }

    @Test
    public void TestAPI() {

        Call<RestNumbersAPI> call = Singletons.getgerritAPIInstance().GetJson("0", "true");
        call.enqueue(new Callback<RestNumbersAPI>() {
            @Override
            public void onResponse(@NonNull Call<RestNumbersAPI> call, @NonNull Response<RestNumbersAPI> response) {

                if (response.isSuccessful()) {
                    assertNull(response.body());

                } else {
                    fail();

                }

            }

            @Override
            public void onFailure(@NonNull Call<RestNumbersAPI> call, Throwable t) {
                fail();

            }
        });
    }
}