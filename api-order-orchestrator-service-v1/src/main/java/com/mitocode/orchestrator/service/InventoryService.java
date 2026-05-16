package com.mitocode.orchestrator.service;

import com.mitocode.orchestrator.client.inventories.restclient.InventoryServiceV1RestClient;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class InventoryService {

    private final InventoryServiceV1RestClient client;

    public void reserveWarehouse(UUID orderId, CreateOrderOrchestratorRequest createOrderRequest) {
        log.info("Reserving warehouse for orderId: {}", orderId);
        client.reserverOrder(orderId, createOrderRequest);
    }

    public void releaseReservation(String orderId, Long warehouseId) {
        ResponseEntity<Void> response = client.releaseOrder(UUID.fromString(orderId), warehouseId);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Warehouse Order Released successfully");
        }
    }
}
