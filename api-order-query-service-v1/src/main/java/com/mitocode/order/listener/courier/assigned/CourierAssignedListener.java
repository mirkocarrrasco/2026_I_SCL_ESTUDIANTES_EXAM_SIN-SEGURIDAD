package com.mitocode.order.listener.courier.assigned;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.order.infraestructure.document.ShipmentAddressDocument;
import com.mitocode.order.infraestructure.document.ShipmentCompanyDocument;
import com.mitocode.order.infraestructure.document.OrderDocument;
import com.mitocode.order.infraestructure.repository.OrderRepository;
import com.mitocode.order.listener.courier.assigned.event.CourierAssignedEvent;

import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CourierAssignedListener {

    private final OrderRepository orderRepository;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "shipment.courier-assigned.v1", groupId = "shipment.courier-assigned.v1.group")
    public void handle(String message) {
        try {

            log.info("Updated Courier Assigned info from message: {}", message);
            CourierAssignedEvent event = mapper.readValue(message, CourierAssignedEvent.class);

            OrderDocument orderDocument = orderRepository.findById(event.getOrderId()).orElseThrow(() -> new NotFoundException("Order Not Found"));

            ShipmentAddressDocument shipmentAddress = new ShipmentAddressDocument();
            shipmentAddress.setAddress(event.getAddress());
            shipmentAddress.setLongitude(event.getLongitude());
            shipmentAddress.setLatitude(event.getLatitude());
            shipmentAddress.setReference(event.getReference());

            orderDocument.setShipmentAddress(shipmentAddress);

            ShipmentCompanyDocument shipmentCompany = new ShipmentCompanyDocument();
            shipmentCompany.setId(event.getShipmentCompany().getId());
            shipmentCompany.setName(event.getShipmentCompany().getName());
            shipmentCompany.setPhoneNumber(event.getShipmentCompany().getPhoneNumber());
            shipmentCompany.setWebSite(event.getShipmentCompany().getWebSite());
            shipmentCompany.setEmail(event.getShipmentCompany().getEmail());

            orderDocument.setShipmentCompany(shipmentCompany);

            orderRepository.save(orderDocument);
        } catch (Exception ex) {
            log.error("Error trying to process Courier Assigned Event", ex);
        }
    }
}
