package com.velocia.gatewayserver.domain.openapi;

import com.velocia.gatewayserver.global.data.FilterConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class OpenAPILogger extends AbstractGatewayFilterFactory<FilterConfig> {
    private static final Logger LOGGER = LogManager.getLogger(OpenAPILogger.class);

    public OpenAPILogger() {
        super(FilterConfig.class);
    }

    @Override
    public GatewayFilter apply(FilterConfig config) {
        return (exchange, chain) -> {
            LOGGER.info("OpenAPILogger USAGE | | | | -> " + config.getUsage());
            LOGGER.info("OpenAPILogger START | | | | -> " + exchange.getRequest().getPath());
            return chain.filter(exchange).then(Mono.fromRunnable(() ->
                    LOGGER.info("OpenAPILogger END | | | | -> " + exchange.getResponse())
            ));
        };
    }
}
