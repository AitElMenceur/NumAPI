package com.example.projetandroid.data;

import com.example.projetandroid.presentation.model.RestNumbersAPI;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GerritAPI {
    static final String TRIVIA = "/{id}/trivia?";

    @Headers("x-rapidapi-key: 263fd9d610msha02b01f6fe3cf63p12cc64jsn8d4a947bd393")
    @GET(TRIVIA)
    Call<RestNumbersAPI> GetJson(
            @Path("id") String number,
            @Query("json") String json

    );
}