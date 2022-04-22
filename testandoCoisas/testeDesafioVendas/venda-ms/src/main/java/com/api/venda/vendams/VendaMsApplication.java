package com.api.venda.vendams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VendaMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendaMsApplication.class, args);
	}

}
