package com.reportform.mapper;

import com.reportform.entity.Order;
import com.reportform.pojo.VendSummary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author xun
 * @create 2022/5/27 15:27
 */
@Mapper
@Repository
public interface ProMapper {
    void add(@Param("proid") Integer proId, @Param("platid") Integer platId, @Param("zone") String zone, @Param("time") LocalDateTime time);

    Integer findIdByProName(@Param("name") String proName);

    Integer findIdByPlatName(@Param("name") String platName);

    List<Order> searchByOrderId(@Param("orderid") Integer orderId);


    void update(@Param("orderid") Integer orderId, @Param("zone") String zone, @Param("proid") Integer proId);

    Integer findOrderId(@Param("proid") Integer proId, @Param("time") String time, @Param("zone") String zone, @Param("platid") Integer platId);

    void delete(@Param("orderid") Integer orderId);

    List<Order> queryAll();

    void insertData(@Param("name") String name, @Param("price") Integer price);

    List<VendSummary> findOrderByTimeAndName(@Param("time") String time);
    List<VendSummary> findSalesByVenName(@Param("time") String time, @Param("vendName") String vendName);

}
