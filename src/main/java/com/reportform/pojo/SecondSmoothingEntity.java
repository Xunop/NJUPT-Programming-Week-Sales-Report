package com.reportform.pojo;

import java.io.Serializable;
import java.util.List;

public class SecondSmoothingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Integer> realDataList;

    private Double lastSinglePredictParam;

    private Double lastSecondPredictParam;

    private int predictTime;

    public int getPredictTime() {
        return predictTime;
    }

    public void setPredictTime(int predictTime) {
        this.predictTime = predictTime;
    }

    public List<Integer> getRealDataList() {
        return realDataList;
    }

    public void setRealDataList(List<Integer> realDataList) {
        this.realDataList = realDataList;
    }

    public Double getLastSinglePredictParam() {
        return lastSinglePredictParam;
    }

    public void setLastSinglePredictParam(Double lastSinglePredictParam) {
        this.lastSinglePredictParam = lastSinglePredictParam;
    }

    public Double getLastSecondPredictParam() {
        return lastSecondPredictParam;
    }

    public void setLastSecondPredictParam(Double lastSecondPredictParam) {
        this.lastSecondPredictParam = lastSecondPredictParam;
    }
}
