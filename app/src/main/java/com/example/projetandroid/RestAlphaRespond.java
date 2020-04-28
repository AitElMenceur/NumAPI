package com.example.projetandroid;

import java.util.List;

public class RestAlphaRespond {
    private List<MetaData> metaDataList;
    private List<TimeSerie> timeSerieList;

    public RestAlphaRespond(List<MetaData> metaDataList, List<TimeSerie> timeSerieList) {
        this.metaDataList = metaDataList;
        this.timeSerieList = timeSerieList;
    }

    public List<MetaData> getMetaDataList() {
        return metaDataList;
    }

    public List<TimeSerie> getTimeSerieList() {
        return timeSerieList;
    }
}
