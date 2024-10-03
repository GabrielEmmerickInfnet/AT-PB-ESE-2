package com.simplespdv.gateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    info =
        @Info(
            title = "Gateway Service",
            description =
                "Centraliza as entrada das requisições, roteando e balanceando a carga entre múltiplos microservices em um sistema distribuído, garantindo segurança e controle sobre as APIs."))
@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
