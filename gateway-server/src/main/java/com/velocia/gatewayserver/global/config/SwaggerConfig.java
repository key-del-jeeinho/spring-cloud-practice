package com.velocia.gatewayserver.global.config;

import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public CommandLineRunner openApi(RouteDefinitionLocator locator,
                                     SwaggerUiConfigParameters swaggerUiConfigParameters) {
        System.out.println("테스트\n" + locator
                .getRouteDefinitions().collectList().block().stream()
                .map(RouteDefinition::getId).toList());
        return args -> locator
                .getRouteDefinitions().collectList().block().stream()
                .map(RouteDefinition::getId)
                .filter(id -> id.matches(".*-api"))
                .forEach(swaggerUiConfigParameters::addGroup);
    }
}
