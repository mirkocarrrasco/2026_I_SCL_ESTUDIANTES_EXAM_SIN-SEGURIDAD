package com.mitocode.orchestrator.client.orders.httpexchange;

import com.mitocode.orchestrator.client.orders.OrderServiceV1Client;
import com.mitocode.orchestrator.client.orders.restclient.dto.CreateOrderRequest;
import com.mitocode.orchestrator.client.orders.restclient.dto.CreateOrderResponse;
import com.mitocode.orchestrator.client.orders.restclient.dto.CustomerRequest;
import com.mitocode.orchestrator.client.orders.restclient.dto.WarehouseRequest;
import com.mitocode.orchestrator.controller.dto.CreateOrderOrchestratorRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
@Profile("HttpExchange")
public class OrderServiceV1HttpExchange implements OrderServiceV1Client {

    private final OrderServiceV1HttpExchangeClient orderClient;

    public CreateOrderResponse createOrder(CreateOrderOrchestratorRequest createOrderOrchestratorRequest) {
        log.info("HttpExchange - Creating order for customer: {}", createOrderOrchestratorRequest.customer().name());

        CreateOrderRequest request = new CreateOrderRequest(
                new CustomerRequest(createOrderOrchestratorRequest.customer().id(), createOrderOrchestratorRequest.customer().name()),
                new WarehouseRequest(createOrderOrchestratorRequest.warehouse().id(), createOrderOrchestratorRequest.warehouse().name()),
                createOrderOrchestratorRequest.total()
        );

        return orderClient.create(request);
    }

    @Override
    public void cancelOrder(String orderId, String reason) {
        //Not Implemented
    }

    @Override
    public void updateOrderStatus(String orderId, String status) {
        //Not Implemented
    }
}
