package com.api.cadastro.cadastroms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
public class CadastroMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroMsApplication.class, args);
	}

}
