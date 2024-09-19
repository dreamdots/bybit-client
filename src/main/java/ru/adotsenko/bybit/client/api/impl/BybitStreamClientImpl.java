package ru.adotsenko.bybit.client.api.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Mono;
import ru.adotsenko.bybit.client.api.BybitStreamClient;
import ru.adotsenko.bybit.client.configuration.BybitApiProperties;
import ru.adotsenko.bybit.client.constant.BybitUri;
import ru.adotsenko.bybit.client.constant.OrderbookStreamDepth;
import ru.adotsenko.bybit.client.constant.StreamTemplate;
import ru.adotsenko.bybit.client.constant.TypeReferenceFactory;
import ru.adotsenko.bybit.client.model.BybitWsResponse;
import ru.adotsenko.bybit.client.model.impl.Interval;
import ru.adotsenko.bybit.client.model.impl.ws.Kline;
import ru.adotsenko.bybit.client.model.impl.ws.Orderbook;
import ru.adotsenko.bybit.client.model.impl.ws.Ticker;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
public class BybitStreamClientImpl implements BybitStreamClient {
    private final BybitApiProperties bybitApiProperties;
    private ObjectMapper objectMapper;

    @PostConstruct
    private void initialize() {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
    }

    @Override
    public Mono<Void> subscribeToOrderBookStream(Consumer<BybitWsResponse<Orderbook>> processor,
                                                 Map<String, OrderbookStreamDepth> symbols) {
        return Mono.fromSupplier(() -> buildOrderbookArgs(symbols))
                .doOnNext(args -> log.info("Subscribe to orderbook stream with args {}", args))
                .map(this::buildSubscribeRequest)
                .flatMap(subscribeRequest -> {
                    var client = new ReactorNettyWebSocketClient();
                    return client.execute(
                            BybitUri.getPublicUri(bybitApiProperties.getMode()),
                            new BybitWebSocketHandler<>(
                                    msg -> map(msg, TypeReferenceFactory.ORDERBOOK_TYPEREF),
                                    processor,
                                    subscribeRequest
                            )
                    );
                })
                .then();
    }

    @Override
    public Mono<Void> subscribeToKlineStream(Consumer<BybitWsResponse<List<Kline>>> processor,
                                             Map<String, Interval> symbols) {
        return Mono.fromSupplier(() -> buildKlineArgs(symbols))
                .doOnNext(args -> log.info("Subscribe to kline stream with args {}", args))
                .map(this::buildSubscribeRequest)
                .flatMap(subscribeRequest -> {
                    var client = new ReactorNettyWebSocketClient();
                    return client.execute(
                            BybitUri.getPublicUri(bybitApiProperties.getMode()),
                            new BybitWebSocketHandler<>(
                                    msg -> map(msg, TypeReferenceFactory.KLINE_TYPEREF),
                                    processor,
                                    subscribeRequest
                            )
                    );
                })
                .then();
    }

    @Override
    public Mono<Void> subscribeToTickerStream(Consumer<BybitWsResponse<Ticker>> processor,
                                              Set<String> symbols) {
        return Mono.fromSupplier(() -> buildTickerArgs(symbols))
                .doOnNext(args -> log.info("Subscribe to ticker stream with args {}", args))
                .map(this::buildSubscribeRequest)
                .flatMap(subscribeRequest -> {
                    var client = new ReactorNettyWebSocketClient();
                    return client.execute(
                            BybitUri.getPublicUri(bybitApiProperties.getMode()),
                            new BybitWebSocketHandler<>(
                                    msg -> map(msg, TypeReferenceFactory.TICKER_TYPEREF),
                                    processor,
                                    subscribeRequest
                            )
                    );
                })
                .then();
    }

    private SubscribeRequest buildSubscribeRequest(List<String> args) {
        return SubscribeRequest
                .builder()
                .operation(SubscribeRequest.Operation.SUBSCRIBE)
                .args(args)
                .requestId(UUID.randomUUID().toString())
                .build();
    }

    private List<String> buildOrderbookArgs(Map<String, OrderbookStreamDepth> symbols) {
        return symbols.entrySet()
                .stream()
                .map(e -> String.format(StreamTemplate.ORDERBOOK_STREAM_TEMPLATE, e.getValue().getDepth(), e.getKey()))
                .toList();
    }

    private List<String> buildKlineArgs(Map<String, Interval> symbols) {
        return symbols
                .entrySet()
                .stream()
                .map(e -> String.format(StreamTemplate.KLINE_STREAM_TEMPLATE, e.getValue().getInterval(), e.getKey()))
                .toList();
    }

    private List<String> buildTickerArgs(Set<String> symbols) {
        return symbols
                .stream()
                .map(e -> String.format(StreamTemplate.TICKER_STREAM_TEMPLATE, e))
                .toList();
    }

    private <T> T map(String msg,
                      TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(msg, typeReference);
        } catch (final Exception e) {
            throw new BybitApiException(e);
        }
    }

    @Data
    @Builder
    public static class SubscribeRequest {
        @JsonProperty("op")
        private Operation operation;
        @JsonProperty("req_id")
        private String requestId;
        @JsonProperty("args")
        private List<String> args;

        @Getter
        @RequiredArgsConstructor
        public enum Operation {
            @JsonProperty("unsubscribe")
            UNSUBSCRIBE("unsubscribe"),
            @JsonProperty("subscribe")
            SUBSCRIBE("subscribe");

            private final String operation;
        }
    }
}
