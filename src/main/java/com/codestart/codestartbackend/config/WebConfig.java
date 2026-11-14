package com.codestart.codestartbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(
                        "https://farooq2514.github.io",
                        "http://127.0.0.1:5500",
                        "http://localhost:5500")
                .allowedMethods("POST", "GET", "OPTIONS")
                .allowedHeaders("*");
    }
}