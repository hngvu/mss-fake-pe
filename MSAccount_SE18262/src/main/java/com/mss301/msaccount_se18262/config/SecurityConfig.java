package com.mss301.msaccount_se18262.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.crypto.spec.SecretKeySpec;
import java.util.List;

@Configuration
public class SecurityConfig {



    @Value("${app.jwt.secret}")
    String jwtSecret;


    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(
                new ImmutableSecret<>(
                        new SecretKeySpec(jwtSecret.getBytes(), "HS256")));
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url("http://localhost:8080/account").description("API Gateway"),
                        new Server().url("https://locahost:8081").description("Account Service")
                ));
    }
}
