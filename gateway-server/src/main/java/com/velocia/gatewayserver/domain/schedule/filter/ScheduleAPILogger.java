package com.velocia.gatewayserver.domain.schedule.filter;

import com.velocia.gatewayserver.global.data.FilterConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ScheduleAPILogger extends AbstractGatewayFilterFactory<FilterConfig> {
    private static final Logger LOGGER = LogManager.getLogger(ScheduleAPILogger.class);

    public ScheduleAPILogger() {
        super(FilterConfig.class);
    }

    @Override
    public GatewayFilter apply(FilterConfig config) {
        return (exchange, chain) -> {
            LOGGER.info("ScheduleAPILogger USAGE | | | | -> " + config.getUsage());
            LOGGER.info("ScheduleAPILogger START | | | | -> " + exchange.getRequest());
            return chain.filter(exchange).then(Mono.fromRunnable(() ->
                    LOGGER.info("ScheduleAPILogger END | | | | -> " + exchange.getResponse())
            ));
        };
    }
}
