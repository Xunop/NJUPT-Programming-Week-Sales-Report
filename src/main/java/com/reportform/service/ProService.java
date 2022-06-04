package com.reportform.service;

import com.reportform.entity.Order;
import com.reportform.pojo.SecondSmoothingEntity;
import com.reportform.pojo.VendSummary;
import com.reportform.util.DataForecast;

import java.util.List;

/**
 * @Author xun
 * @create 2022/5/27 15:36
 */
public interface ProService {
    String add(String proName, String platName, String zone);

    List<Order> search(String zone, String proName, String platName, Integer orderId, String time, Integer itemsPerPage, Integer pageNum);
    String update(Integer orderId, String zone, String proName);

    String delete(Integer orderId);

    List<VendSummary> statistics(String time);
    List<VendSummary> querySales(String time, String vendName);

    Integer totalPageNum(String zone, String proName, String platName, Integer orderId, String time);

    Integer forecast(String vendName, List<Integer> realDataList, int sum, double avg);
}
