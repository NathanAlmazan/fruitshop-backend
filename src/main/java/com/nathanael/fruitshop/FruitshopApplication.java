package com.nathanael.fruitshop;

import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FruitshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(FruitshopApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins("*")
						.allowedHeaders("Content-Type", "Accept")
						.allowedMethods("POST", "GET", "DELETE");
			}
		};
	}

}
