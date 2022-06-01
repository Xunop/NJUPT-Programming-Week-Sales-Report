package com.reportform.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author xun
 * @create 2022/5/27 21:53
 */
@Data
public class Order {
    private String zone;
    private Integer orderId;
    private String time;
    private String platName;
    private String vendName;
    private String proName;
    private Integer price;
}
