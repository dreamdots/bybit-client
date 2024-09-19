package ru.adotsenko.bybit.client.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.adotsenko.bybit.client.api.BybitClient;
import ru.adotsenko.bybit.client.configuration.BybitApiProperties;
import ru.adotsenko.bybit.client.model.BybitResponse;
import ru.adotsenko.bybit.client.model.impl.Category;
import ru.adotsenko.bybit.client.model.impl.Interval;
import ru.adotsenko.bybit.client.model.impl.KlineList;
import ru.adotsenko.bybit.client.model.impl.ServerTime;

import java.net.URI;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
public class BybitClientImpl implements BybitClient {
    private final BybitApiProperties bybitApiProperties;
    private final WebClient webClient;

    @Override
    public Mono<BybitResponse<ServerTime>> serverTime() {
        return get("/v5/market/time")
                .bodyToMono(new ParameterizedTypeReference<BybitResponse<ServerTime>>() {
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<BybitResponse<KlineList>> kline(Category category,
                                                String symbol,
                                                Interval interval,
                                                Long startTimestamp,
                                                Long endTimestamp,
                                                Integer limit
    ) {
        final Function<UriBuilder, URI> builder = b -> b
                .queryParam("category", category.getCategory())
                .queryParam("symbol", symbol)
                .queryParam("interval", interval.getInterval())
                .queryParam("start", startTimestamp)
                .queryParam("end", endTimestamp)
                .queryParam("limit", limit)
                .build();

        return get("/v5/market/kline", builder)
                .bodyToMono(new ParameterizedTypeReference<BybitResponse<KlineList>>() {
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    private WebClient.ResponseSpec get(String uri) {
        return get(uri, UriBuilder::build);
    }

    private WebClient.ResponseSpec get(String uri,
                                       Function<UriBuilder, URI> builder) {
        return webClient.get()
                .uri(uri, builder)
                .retrieve()
                .onRawStatus(status -> status != 200, r -> Mono.error(new BybitApiException()));
    }
}
