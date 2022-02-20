package com.velocia.gatewayserver.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

@Component
public class CorsFilter {
        @Bean
        public CorsWebFilter corsWebFilter(CorsConfigurationSource source) {
            return new CorsWebFilter(source);
        }
}
