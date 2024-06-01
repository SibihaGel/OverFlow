package com.javamentor.qa.platform.webapp.configs;



import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


@Configuration
public class SwaggerConfig {

    @Value(value = "${swagger.server.url}")
    private String url;



    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Kata")
                        .version("1.0.0")
                        .description("API приложений"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication",new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .servers(List.of(new Server()
                        .url(url)
                        .description("DevService")))
                .security(Arrays.asList(new SecurityRequirement().addList("bearerAuth")))
                .info(new Info()
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .info(new Info()
                        .contact(
                                new Contact()
                                        .email("dev.devops@gmail.com")
                                        .name("Dev Devops")
                        ));
    }

}

