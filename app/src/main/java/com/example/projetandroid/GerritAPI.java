package com.example.projetandroid;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GerritAPI {
    static final String URI_GET_MATH = "/{id}/trivia?";

    @Headers("x-rapidapi-key: 263fd9d610msha02b01f6fe3cf63p12cc64jsn8d4a947bd393")
    @GET(URI_GET_MATH)
    Call<RestNumbersAPI> GetJson(
            @Path("id") String number,
            @Query("json") String json

    );
}