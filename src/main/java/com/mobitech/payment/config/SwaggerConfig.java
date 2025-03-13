package com.mobitech.payment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI paymentsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Payment API")
                        .description("API for processing B2C payments and managing customers")
                        .version("1.0.0"));
    }
}
