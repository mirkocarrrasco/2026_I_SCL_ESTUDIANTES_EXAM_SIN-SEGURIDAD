package com.mitocode.inventory.controller;

import com.mitocode.inventory.controller.mapper.OrderControllerMapper;
import com.mitocode.inventory.domain.Order;
import com.mitocode.inventory.dto.ReleaseOrderRequest;
import com.mitocode.inventory.dto.ReserveOrderRequest;
import com.mitocode.inventory.dto.ReserveOrderResponse;
import com.mitocode.inventory.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventories")
public class OrderController {

    private final OrderService service;
    private final OrderControllerMapper mapper;

    public OrderController(OrderService service, OrderControllerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/{warehouseId}/orders/reserve")
    public ResponseEntity<ReserveOrderResponse> reserveOrder(
            @PathVariable Long warehouseId,
            @RequestBody ReserveOrderRequest request) {

        ReserveOrderRequest updatedRequest = new ReserveOrderRequest(
                request.orderId(),
                warehouseId,
                request.customerId(),
                request.items()
        );

        Order order = mapper.toDomain(updatedRequest);

        Order reservedOrder = service.reserveOrder(order);
        return ResponseEntity.ok(mapper.toResponse(reservedOrder));
    }

    @PostMapping("/{warehouseId}/orders/release")
    public ResponseEntity<ReserveOrderResponse> releaseOrder(
            @PathVariable Long warehouseId,
            @RequestBody ReleaseOrderRequest request) {

        service.releaseOrder(warehouseId, request.orderId());

        return ResponseEntity.ok().build();
    }
}