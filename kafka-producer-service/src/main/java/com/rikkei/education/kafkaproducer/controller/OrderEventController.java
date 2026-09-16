package com.rikkei.education.kafkaproducer.controller;

import com.rikkei.education.kafkaproducer.dto.OrderEvent;
import com.rikkei.education.kafkaproducer.service.OrderEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderEventController {

    private final OrderEventProducer orderEventProducer;

    @PostMapping
    public ResponseEntity<OrderEvent> createOrder(@RequestBody OrderEvent orderEvent) {
        if (orderEvent.getOrderId() == null || orderEvent.getOrderId().isBlank()) {
            orderEvent.setOrderId(UUID.randomUUID().toString());
        }
        orderEvent.setStatus("CREATED");
        orderEventProducer.publish(orderEvent);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderEvent);
    }
}
