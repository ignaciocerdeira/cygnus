package com.atsistemas.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class Application {

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 */
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}
}
