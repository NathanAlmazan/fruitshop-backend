package com.ntea.fruitshop;

import com.ntea.fruitshop.users.AccountDetailService;
import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@SpringBootApplication
public class FruitshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(FruitshopApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new AccountDetailService();
	}

	@Bean
	public WebMvcConfigurer corsConfigure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins("*")
						.allowedHeaders("Content-Type", "Accept", AUTHORIZATION)
						.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS");
			}
		};
	}

}
