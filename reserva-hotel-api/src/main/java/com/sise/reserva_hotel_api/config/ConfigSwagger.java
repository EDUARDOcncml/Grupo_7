package com.sise.reserva_hotel_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class ConfigSwagger {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
        .info( new Info()
                .title("API de Reserva de Hotel")
                .version("1.0")
                .description("Laa Documentacion de la API de Reserva de Hotel"));
    }

}
