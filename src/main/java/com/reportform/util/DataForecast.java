package com.reportform.util;

import com.reportform.pojo.SecondSmoothingEntity;

import java.util.*;

/**
 * @Author xun
 * @create 2022/6/3 15:49
 */
public class DataForecast {
    public static int secondExponentialSmoothingMethod(SecondSmoothingEntity secondSmoothingEntity) {
        /** 二次指数平滑公式：
         * St2 = a * S`t(一次平滑得到的预估值) + (1-a) * S2t-1 (上一次的二次平滑预估值)
         * At = 2 * St1 - St2
         * Bt = a / 1-a * (St1 - St2)
         * ^Yt+T (T为将来预测期数）= At + Bt * T
         **/
        // （1）获取实际观察值列表和最后一次的预测值
        List<Integer> realParamList = secondSmoothingEntity.getRealDataList();
        Integer realData = realParamList.get(4);
        Double lastSinglePredictParam = secondSmoothingEntity.getLastSinglePredictParam();
        Double lastSecondPredictParam = secondSmoothingEntity.getLastSecondPredictParam();
        int predictTime = secondSmoothingEntity.getPredictTime();
        double yt_T = lastSecondPredictParam;
        // （2）平滑值区间 [1~10]/10,先做一次平滑
        double smoothParam = 0.6;
        // 获得一次平滑预测值
        lastSinglePredictParam = smoothParam * realData + (1 - smoothParam) * lastSinglePredictParam;
        // 计算二次平滑值
        lastSecondPredictParam = smoothParam * lastSinglePredictParam + (1 - smoothParam) * lastSecondPredictParam;
        double at = 2 * lastSinglePredictParam - lastSecondPredictParam;
        double bt = smoothParam / (1 - smoothParam) * (lastSinglePredictParam - lastSecondPredictParam);
        // 计算出预测值
        yt_T = at + bt * predictTime;
        int y = (int) yt_T;
        return y;
    }
}
