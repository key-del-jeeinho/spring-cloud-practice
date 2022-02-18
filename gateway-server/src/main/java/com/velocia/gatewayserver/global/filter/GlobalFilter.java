package com.velocia.gatewayserver.global.filter;

import com.velocia.gatewayserver.global.data.FilterConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<FilterConfig> {
    private static final Logger LOGGER = LogManager.getLogger(GlobalFilter.class);

    public GlobalFilter() {
        super(FilterConfig.class);
    }

    @Override
    public GatewayFilter apply(FilterConfig config) {
        return (exchange, chain) -> {
            LOGGER.info("GlobalFilter USAGE | | | | -> " + config.getUsage());
            LOGGER.info("GlobalFilter START | | | | -> " + exchange.getRequest().getPath());
            return chain.filter(exchange).then(Mono.fromRunnable(() ->
                    LOGGER.info("GlobalFilter END | | | | -> " + exchange.getResponse())
            ));
        };
    }
}
