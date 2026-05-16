package com.mitocode.inventory.service;

import com.mitocode.inventory.domain.Order;
import com.mitocode.inventory.producer.inventory.order.released.InventoryOrderCancelledProducer;
import com.mitocode.inventory.producer.inventory.order.reserved.InventoryOrderReservedProducer;
import com.mitocode.inventory.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final InventoryOrderReservedProducer orderReservedProducer;
    private final InventoryOrderCancelledProducer orderCancelledProducer;

    public Order reserveOrder(Order order) {
        Order saved = repository.save(order);

        orderReservedProducer.produce(saved);

        return saved;
    }

    public void releaseOrder(Long warehouseId, UUID orderId) {
        log.info("Releasing Order {} for Warehouse {}", orderId, warehouseId);

        Order order = repository.getBy(warehouseId, orderId);

        order.setStatus("CANCELLED_BY_WAREHOUSE");
        order.setReason("Some reason to cancel the order");

        repository.save(order);
        //hace algo para notificar al warehouse
        orderCancelledProducer.produce(order);
    }
}
