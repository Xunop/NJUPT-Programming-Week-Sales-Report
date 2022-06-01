package com.reportform.mapper;

import com.reportform.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author xun
 * @create 2022/5/30 15:00
 */
@Mapper
@Repository
public interface SearchMapper {
    List<Order> searchByAll(@Param("proId") Integer proId,
                            @Param("platId") Integer platId,
                            @Param("zone") String zone,
                            @Param("time") String time,
                            @Param("orderId") Integer orderId,
                            @Param("itemsPerPage") int itemsPerPage,
                            @Param("offset") int offset);

    List<Order> searchAll(@Param("proId") Integer proId,
                            @Param("platId") Integer platId,
                            @Param("zone") String zone,
                            @Param("time") String time,
                            @Param("orderId") Integer orderId);
}
