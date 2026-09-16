package com.rikkei.education.kafkaconsumer.listener;

import com.rikkei.education.kafkaconsumer.dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventListener {

    @KafkaListener(
            topics = "${app.kafka.topic.order-events}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void onOrderEvent(ConsumerRecord<String, OrderEvent> record) {
        OrderEvent event = record.value();
        log.info("Consumed order event: key={}, partition={}, offset={}, event={}",
                record.key(), record.partition(), record.offset(), event);
    }
}
