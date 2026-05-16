package com.mitocode.orchestrator.service.saga;

import com.mitocode.orchestrator.service.ShipmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@Order(500)
@AllArgsConstructor
public class ShipmentStep implements SagaStep {

    private final ShipmentService shipmentService;

    @Override
    public void execute(CreateOrderSagaContext context) {
        log.info("Executing ShipmentStep for order {}", context.getOrderId());
        shipmentService.assignCourier(UUID.fromString(context.getOrderId()), context.getRequest());
    }

    @Override
    public void compensate(CreateOrderSagaContext context) {
        log.info("Compensating ShipmentStep: unassigning courier for order {}", context.getOrderId());
        shipmentService.releaseCourier(UUID.fromString(context.getOrderId()));
    }
}
