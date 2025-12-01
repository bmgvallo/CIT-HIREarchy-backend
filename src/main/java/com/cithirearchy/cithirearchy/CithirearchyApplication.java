package com.cithirearchy.cithirearchy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SpringBootApplication
public class CithirearchyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CithirearchyApplication.class, args);
	}

}