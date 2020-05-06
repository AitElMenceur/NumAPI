package com.example.projetandroid;


import com.google.gson.annotations.*;

public class RestAlphaRespond {

    @SerializedName("Meta Data")
    private MetaData metaData;

    @SerializedName("Time Series (5min)")
    private TimeSerial timeSerial;

    public MetaData getMetaData() {
        return metaData;
    }

    public TimeSerial getTimeSerie() {
        return timeSerial;
    }
}
