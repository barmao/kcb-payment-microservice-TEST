package com.mobitech.payment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure JWT authentication converter
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Swagger UI related paths
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()

                        // Other public endpoints
                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()

                        // Payment API endpoints
                        .requestMatchers("/api/v1/payments/**").permitAll()
                        .requestMatchers("/api/payments/callback/**").permitAll()

                        // Payment endpoints that require authentication
                        .requestMatchers("/api/payments/b2c").hasAuthority("SCOPE_payment:write")
                        .requestMatchers("/api/payments/*/status").hasAuthority("SCOPE_payment:read")
                        .requestMatchers("/api/dev/token").permitAll()

                        // All other endpoints require authentication
                        .anyRequest().authenticated()
                )
                // Configure OAuth2 resource server with JWT
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter)
                        )
                );

        return http.build();
    }
}