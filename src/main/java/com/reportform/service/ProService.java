package com.reportform.service;

import com.reportform.entity.Order;
import com.reportform.pojo.VendSummary;

import java.util.List;

/**
 * @Author xun
 * @create 2022/5/27 15:36
 */
public interface ProService {
    String add(String proName, String platName, String zone);

    List<Order> search(String zone, String proName, String platName, Integer orderId, String time, Integer itemsPerPage, Integer pageNum);

    String update1(String proName1, String time, String zone1, String platName, String zone2, String proName2);
    String update(Integer orderId, String zone, String proName);

    String deleteBy(String proName, String time, String zone, String platName);
    String delete(Integer orderId);
    List<Order> queryAll();

    List<VendSummary> statistics(String time);
    List<VendSummary> querySales(String time, String vendName);

    Integer TotalPage(String zone, String proName, String platName, Integer orderId, String time);
}
