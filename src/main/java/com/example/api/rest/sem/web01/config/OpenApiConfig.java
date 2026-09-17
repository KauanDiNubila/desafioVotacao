package com.example.api.rest.sem.web01.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Votação em Assembleias")
                        .description("Cadastro de associados e pautas, abertura de sessão de votação com prazo e apuração de resultado")
                        .version("v1"));
    }
}
