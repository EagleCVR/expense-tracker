package com.vaibhav.expensetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ✅ INTERVIEW-READY EXPLANATION:
 * 
 * This class configures Cross-Origin Resource Sharing (CORS) globally in a Spring Boot application.
 * 
 * ⚙️ CORS allows the frontend (which runs on a different origin like http://localhost:3000) 
 * to communicate with the backend (typically running on http://localhost:8080).
 * 
 * Without this configuration, modern browsers will block requests made from the frontend
 * to the backend due to the Same-Origin Policy (a security feature).
 * 
 * 📌 In a real-world application, allowedOrigins should be more restrictive in production.
 * 
 * ✅ ALTERNATIVES:
 * - Use @CrossOrigin on controller methods or classes (controller-level CORS).
 * - Use filters for advanced control (e.g., if using Spring Security).
 */
@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            // This method is called by Spring Boot to register CORS mappings
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply this CORS policy to all endpoints
                        .allowedOrigins("http://localhost:3000") // ✅ Only allow requests from React dev server
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow only required HTTP methods
                        .allowedHeaders("*"); // Accept all headers in the request
            }
        };
    }
}
