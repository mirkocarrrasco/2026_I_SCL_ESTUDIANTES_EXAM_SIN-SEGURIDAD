package com.mitocode.order.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WarehouseResponse {
    private Long id;
    private String name;
}
