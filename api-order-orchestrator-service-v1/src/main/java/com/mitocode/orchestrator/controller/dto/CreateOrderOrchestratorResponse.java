package com.mitocode.orchestrator.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Response object for creating an order")
public record CreateOrderOrchestratorResponse(
        @Schema(name = "OtroValorDePrueba", description = "The unique identifier of the created order", example = "777e7777-e89b-12d3-a456-426614174000")
        UUID id
) {
}
