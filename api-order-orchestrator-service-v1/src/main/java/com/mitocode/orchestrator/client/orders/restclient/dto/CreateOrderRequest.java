package com.mitocode.orchestrator.client.orders.restclient.dto;

import java.math.BigDecimal;

public record CreateOrderRequest(CustomerRequest customer,
                                 WarehouseRequest warehouse,
                                 BigDecimal total) {
}
