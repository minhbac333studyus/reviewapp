package com.rp;

import com.rp.repositories.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@SpringBootApplication
public class ReviewProductApplication {
    @Value("${server.frontend.host}")
    private String frontendHost;

    public static void main(String[] args) {
        SpringApplication.run(ReviewProductApplication.class, args);
    }

    @Bean
    CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList(frontendHost));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin",
                "Access-Control-Allow-Origin",
                "Content-Type",
                "Accept",
                "Jwt-Token",
                "Authorization",
                "X-Requested-With",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers",
                "Access-Control-Allow-Credential",
                "File-Name"));
        //This code is for working when upload object with json style
        corsConfiguration.setAllowedHeaders(Arrays.asList("Content-Type", "*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
