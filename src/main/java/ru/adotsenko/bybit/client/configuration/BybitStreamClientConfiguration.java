package ru.adotsenko.bybit.client.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.adotsenko.bybit.client.api.BybitStreamClient;
import ru.adotsenko.bybit.client.api.impl.BybitStreamClientImpl;

@Configuration
@ConditionalOnProperty(prefix = "ru.adotsenko.bybit.client", value = "ws-enabled", havingValue = "true")
@EnableConfigurationProperties({BybitApiProperties.class})
public class BybitStreamClientConfiguration {

    @Bean
    public BybitStreamClient bybitStreamClient(BybitApiProperties bybitApiProperties) {
        return new BybitStreamClientImpl(bybitApiProperties);
    }
}
