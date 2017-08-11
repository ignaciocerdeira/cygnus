package com.atsistemas.cygnus.services.user;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.atsistemas.cygnus.services.user.model.User;

@SpringBootApplication
@EnableDiscoveryClient
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner(final UserRepository userRepository) {
		return (args) -> {
			String[] array = { "Howard", "Leonard", "Raj", "Sheldon" };
			Arrays.asList(array).forEach(nombre -> {
				userRepository.save(new User(nombre));
			});
			System.out.println("Listado usuarios: ");
			userRepository.findAll().forEach(usuario -> {
				System.out.println("\t" + usuario);
			});
			System.out.println("User: " + userRepository.findAllByName("Raj"));
		};
	}

}
