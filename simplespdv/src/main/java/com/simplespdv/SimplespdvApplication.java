package com.simplespdv;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
	    info =
	        @Info(
	            title = "SimplesPDV",
	            description = "Microsserviço que integra o sistema PDV"))

@SpringBootApplication
@EnableFeignClients(basePackages = "com.simplespdv.client")
public class SimplespdvApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplespdvApplication.class, args);
	}

}
