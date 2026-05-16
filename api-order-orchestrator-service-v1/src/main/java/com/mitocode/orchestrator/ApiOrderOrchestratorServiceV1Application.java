package com.mitocode.orchestrator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SecuritySchemes({
        @SecurityScheme(
                name = "bearerAuth",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT",
                description = "Autenticación mediante token JWT"
        ),
        @SecurityScheme(
                name = "MutualAuth",
                type = SecuritySchemeType.MUTUALTLS,
                description = "Autenticación mutua TLS"
        ),
        @SecurityScheme(
                name = "ApiKeyAuth",
                type = SecuritySchemeType.APIKEY,
                in = SecuritySchemeIn.HEADER,
                paramName = "X-API-KEY",
                description = "Autenticación mediante API Key en el header"
        )

})
@OpenAPIDefinition(
        info = @Info(title = "Order Orchestrator API", version = "1.0", description = "API Order Orchestrator Service V1 - Orquestación de pedidos"),
        tags = {
                @Tag(name = "Order Orchestrator", description = "Operaciones relacionadas con la orquestación de pedidos")
        },
        servers = {
                @Server(url = "http://localhost:50100/api/v1/order-orchestrator", description = "Servidor local"),
                @Server(url = "http://qa.mitocode.com/api/v1/order-orchestrator", description = "Servidor de QA"),
                @Server(url = "http://mitocode.com/api/v1/order-orchestrator", description = "Servidor de Produccion"),
        }
)
@EnableFeignClients
@SpringBootApplication
public class ApiOrderOrchestratorServiceV1Application {

    public static void main(String[] args) {
        SpringApplication.run(ApiOrderOrchestratorServiceV1Application.class, args);
    }

}
