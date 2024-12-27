package com.docsbymario.mockapi.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi universityApi() {
        return GroupedOpenApi.builder()
                .addOpenApiCustomizer(openApi -> openApi.getInfo().setTitle("University API"))
                .displayName("University API")
                .group("university-api")  // Group name for product APIs
                .pathsToMatch("/university/**")  // Match paths related to products
                .build();
    }
}