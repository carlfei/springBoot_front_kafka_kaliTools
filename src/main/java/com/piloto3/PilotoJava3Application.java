package com.piloto3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
@EnableAsync
@SpringBootApplication
public class PilotoJava3Application {

	public static void main(String[] args) {
		SpringApplication.run(PilotoJava3Application.class, args);

	}

}
