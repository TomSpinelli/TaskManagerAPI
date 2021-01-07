package com.example.TaskManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*Run this Class to start App at http://localhost:8080*/
/*FrontEnd interface must use http://localhost:4200*/
@SpringBootApplication
public class TaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	 @SuppressWarnings("deprecation")
		@Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("PUT", "DELETE", "GET", "POST");
	            }
	        };
	    }
}
