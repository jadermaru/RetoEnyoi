package com.reto.reto;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class RetoApplication {

	public static void main(String[] args) {

		SpringApplication.run(RetoApplication.class, args);
	}

}
