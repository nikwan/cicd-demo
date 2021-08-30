package com.acoustic.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.acoustic.gateway.controllers", "com.acoustic.gateway.routes"})
public class AcousticGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcousticGatewayApplication.class, args);
	}

}
