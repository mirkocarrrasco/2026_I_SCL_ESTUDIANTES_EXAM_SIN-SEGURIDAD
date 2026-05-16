package com.mitocode.order.listener.shipment.started;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.order.infraestructure.document.OrderDocument;
import com.mitocode.order.infraestructure.repository.OrderRepository;
import com.mitocode.order.listener.shipment.started.event.ShipmentStartedEvent;

import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ShipmentStartedListener {

    private final OrderRepository orderRepository;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "shipment.shipment-started.v1", groupId = "shipment.shipment-started.v1.group")
    public void handle(String message) {
        try {

            log.info("UpdatedShipment Started info from message: {}", message);
            ShipmentStartedEvent event = mapper.readValue(message, ShipmentStartedEvent.class);

            OrderDocument orderDocument = orderRepository.findById(event.getOrderId()).orElseThrow(() -> new NotFoundException("Order Not Found"));

            orderDocument.setShipmentStatus(event.getStatus());

            orderRepository.save(orderDocument);
        } catch (Exception ex) {
            log.error("Error trying to process Shipment Started Event", ex);
        }
    }
}
