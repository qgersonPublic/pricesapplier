package com.between.test.infraestructure.adapters.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApi {

    @Bean
    public OpenAPI appOpenApi(){
        return new OpenAPI()
                .info(appInfo());
    }

    private Info appInfo(){
        return new Info()
                .title("Between Test")
                .contact(appContact())
                .description("Test for Prices application")
                .version("1.0.0.SNAPSHOT");
    }


    private Contact appContact(){
        return new Contact()
                .name("Gerson Quintero")
                .email("qgerson@gmail.com")
                .url("mailto:qgerson@gmail.com");
    }
}
