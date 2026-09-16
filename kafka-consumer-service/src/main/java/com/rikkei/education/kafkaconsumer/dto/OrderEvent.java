package com.rikkei.education.kafkaconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {

    private String orderId;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private String status;
}
