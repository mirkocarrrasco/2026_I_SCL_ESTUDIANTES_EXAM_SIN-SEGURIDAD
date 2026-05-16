package com.mitocode.shipment.producer.shipment.started;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.shipment.domain.Shipment;
import com.mitocode.shipment.producer.shipment.started.event.ShipmentStartedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ShipmentStartedProducer {

    private static final String TOPIC = "shipment.shipment-started.v1";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public void produce(Shipment shipment) {

        try {
            ShipmentStartedEvent event = new ShipmentStartedEvent(shipment.getOrderId().toString(), shipment.getStatus().name());

            kafkaTemplate.send(TOPIC, shipment.getOrderId().toString(), mapper.writeValueAsString(event));

            log.info("Shipment Started Event sent");
        } catch (Exception ex) {
            log.error("Error trying to send Shipment Started Event", ex);
        }

    }
}

