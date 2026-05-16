package com.mitocode.order.mapper;

import com.mitocode.order.domain.Customer;
import com.mitocode.order.domain.Order;
import com.mitocode.order.domain.Warehouse;
import com.mitocode.order.dto.request.CreateOrderRequest;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RequestToDomainMapper {

    public static Order toDomain(CreateOrderRequest req) {

        return Order.createNew(
                Customer.of(req.customer().id(), req.customer().name()),
                Warehouse.of(req.warehouse().id(), req.warehouse().name()),
                req.total()
        );
    }

}
