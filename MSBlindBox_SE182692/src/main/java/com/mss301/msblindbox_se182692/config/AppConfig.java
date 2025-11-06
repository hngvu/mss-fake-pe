package com.mss301.msblindbox_se182692.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class AppConfig {

//    @Value("${app.allowed-origins}")
//    String allowedOrigins;
//
//    @Bean
//    public CorsFilter corsFilter(){
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedOrigins(List.of(allowedOrigins.split(",")));
//        corsConfiguration.setAllowedHeaders(List.of("*"));
//        corsConfiguration.setAllowedMethods(List.of("*"));
//        corsConfiguration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//
//        return new CorsFilter(source);
//    }

//    @Value("${app.service.brand.url}")
//    String brandServiceUrl;
//
//    @Bean
//    public BrandServiceClient brandServiceClient() {
//        return Feign.builder()
//                .target(BrandServiceClient.class, brandServiceUrl);
//    }

    @Bean
    OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info()
                        .title("")
                        .version("")
                        .description(""))
                // Add the security scheme globally
                .servers(List.of(
                        // Server qua Gateway
                        new Server()
                                .url("http://localhost:8080/blindbox")
                                .description("API Gateway - Port 8080"),
                        // Server trực tiếp của service
                        new Server()
                                .url("http://localhost:8083")
                                .description("BlindBox Service - Port 8083")
                ))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }

}
