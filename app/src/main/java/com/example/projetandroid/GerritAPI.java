package com.example.projetandroid;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GerritAPI {

    @GET("/query?")
    Call<RestAlphaRespond> getStock(
            @Query("function") String function,
            @Query("symbol") String symbol,
            @Query("interval") String interval,
            @Query("apikey") String apikey);
}