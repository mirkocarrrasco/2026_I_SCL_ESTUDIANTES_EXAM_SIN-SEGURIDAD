package com.mitocode.shipment.producer.shipment.completed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.shipment.domain.Shipment;
import com.mitocode.shipment.producer.shipment.completed.event.ShipmentCompletedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ShipmentCompletedProducer {

    private static final String TOPIC = "shipment.shipment-completed.v1";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public void produce(Shipment shipment) {

        try {
            ShipmentCompletedEvent event = new ShipmentCompletedEvent(shipment.getOrderId().toString(), shipment.getStatus().name());

            kafkaTemplate.send(TOPIC, shipment.getOrderId().toString(), mapper.writeValueAsString(event));

            log.info("Shipment Completed Event sent");
        } catch (Exception ex) {
            log.error("Error trying to send Shipment Completed Event", ex);
        }

    }
}
