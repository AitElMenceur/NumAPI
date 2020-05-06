package com.example.projetandroid;

import com.google.gson.annotations.SerializedName;

public class MetaData {

    @SerializedName("1. Information")
    private String Information;
    @SerializedName("2. Symbol")
    private String Symbol;
    @SerializedName("3. Last Refreshed")
    private String Last_Refreshed;
    @SerializedName("4. Interval")
    private String Interval;
    @SerializedName("5. Output Size")
    private String Output_Size;
    @SerializedName("6. Time Zone")
    private String Time_Zone;

    public String getInformation() {
        return Information;
    }

    public String getSymbol() {
        return Symbol;
    }

    public String getLast_Refreshed() {
        return Last_Refreshed;
    }

    public String getInterval() {
        return Interval;
    }

    public String getOutput_Size() {
        return Output_Size;
    }

    public String getTime_Zone() {
        return Time_Zone;
    }


}
