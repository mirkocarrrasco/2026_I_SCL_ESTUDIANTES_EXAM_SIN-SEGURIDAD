package com.mitocode.inventory.producer.inventory.order.released;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.inventory.domain.Order;
import com.mitocode.inventory.producer.inventory.order.released.event.InventoryOrderCancelledEvent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class InventoryOrderCancelledProducer {

    private static final String TOPIC = "inventory.inventory-order-released.v1";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public void produce(Order order) {

        try {
            InventoryOrderCancelledEvent event = new InventoryOrderCancelledEvent();
            event.setOrderId(order.getOrderId().toString());
            event.setStatus(order.getStatus());
            event.setReason(order.getReason());

            kafkaTemplate.send(TOPIC, order.getOrderId().toString(), mapper.writeValueAsString(event));

            log.info("Inventory Order Released Event send");
        } catch (Exception ex) {
            log.error("Error trying to send Inventory Order Released Event", ex);
        }
    }
}
