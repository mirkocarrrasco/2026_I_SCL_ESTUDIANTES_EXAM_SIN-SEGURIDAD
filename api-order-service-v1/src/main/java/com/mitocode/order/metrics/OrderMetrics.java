package com.mitocode.order.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class OrderMetrics {

    private final MeterRegistry registry;

    public OrderMetrics(MeterRegistry registry) {
        this.registry = registry;
    }

    public void incrementOrdersStatus(String status) {
        registry.counter("order_status_total", "status", status).increment();
    }
}
