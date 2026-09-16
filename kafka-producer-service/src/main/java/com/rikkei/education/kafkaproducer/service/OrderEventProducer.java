package com.rikkei.education.kafkaproducer.service;

import com.rikkei.education.kafkaproducer.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Value("${app.kafka.topic.order-events}")
    private String orderEventsTopic;

    public void publish(OrderEvent orderEvent) {
        kafkaTemplate.send(orderEventsTopic, orderEvent.getOrderId(), orderEvent)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Failed to publish order event {}", orderEvent.getOrderId(), ex);
                    } else {
                        log.info("Published order event {} to partition {}",
                                orderEvent.getOrderId(),
                                result.getRecordMetadata().partition());
                    }
                });
    }
}
