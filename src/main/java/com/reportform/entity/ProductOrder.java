package com.reportform.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author xun
 * @create 2022/5/27 15:04
 */
@Data
public class ProductOrder {
    private String zone;
    private Integer orderId;
    private LocalDateTime time;
}
