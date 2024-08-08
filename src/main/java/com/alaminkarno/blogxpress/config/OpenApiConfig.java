package com.alaminkarno.blogxpress.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Contact contact = new Contact();
        contact.name("Md. Al-Amin");
        contact.email("amin15-1951@diu.edu.bd");
        contact.url("https://github.com/alamin-karno/blogxpress");

        return new OpenAPI()
                .info(new Info().title("BlogXpress API")
                        .version("1.0")
                        .description("BlogXpress: Your ultimate platform for seamless blogging, powered by Spring Boot.")
                        .contact(contact));
    }
}
