package com.example.dboperationproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;


@SpringBootApplication
@EnableJpaAuditing
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	
}
