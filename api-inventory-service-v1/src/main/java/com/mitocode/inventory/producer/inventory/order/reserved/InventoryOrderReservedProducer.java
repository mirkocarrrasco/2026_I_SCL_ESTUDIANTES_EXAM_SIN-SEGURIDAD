package com.mitocode.inventory.producer.inventory.order.reserved;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.inventory.domain.Order;
import com.mitocode.inventory.producer.inventory.order.reserved.event.OrderItemEvent;
import com.mitocode.inventory.producer.inventory.order.reserved.event.InventoryOrderReservedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class InventoryOrderReservedProducer {

    private static final String TOPIC = "inventory.inventory-order-reserved.v1";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public void produce(Order order) {

        try {
            InventoryOrderReservedEvent event = new InventoryOrderReservedEvent();
            event.setOrderId(order.getOrderId().toString());
            event.setItems(order.getItems()
                    .stream()
                    .map(item ->
                            new OrderItemEvent(item.getProductId(), item.getProductName(), item.getQuantity())
                    )
                    .toList()
            );

            kafkaTemplate.send(TOPIC, order.getOrderId().toString(), mapper.writeValueAsString(event));

            log.info("Inventory Order Reserved Event send");
        } catch (Exception ex) {
            log.error("Error trying to send Inventory Order Reserved Event", ex);
        }
    }
}
