package ru.adotsenko.bybit.client.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.adotsenko.bybit.client.api.BybitClient;
import ru.adotsenko.bybit.client.api.impl.BybitClientImpl;
import ru.adotsenko.bybit.client.constant.BybitUri;

@Configuration
@ConditionalOnProperty(prefix = "ru.adotsenko.bybit.client", value = "enabled", havingValue = "true")
@EnableConfigurationProperties({BybitApiProperties.class})
public class BybitClientConfiguration {

    @Bean
    public BybitClient bybitClient(BybitApiProperties bybitApiProperties) {
        return new BybitClientImpl(
                bybitApiProperties,
                WebClient
                        .builder()
                        .baseUrl(BybitUri.getRestUri(bybitApiProperties.getMode()).toString())
                        .build()
        );
    }
}
