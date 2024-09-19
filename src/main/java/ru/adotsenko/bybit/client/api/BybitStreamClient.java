package ru.adotsenko.bybit.client.api;

import reactor.core.publisher.Mono;
import ru.adotsenko.bybit.client.model.impl.Interval;
import ru.adotsenko.bybit.client.constant.OrderbookStreamDepth;
import ru.adotsenko.bybit.client.model.impl.ws.Kline;
import ru.adotsenko.bybit.client.model.impl.ws.Orderbook;
import ru.adotsenko.bybit.client.model.BybitWsResponse;
import ru.adotsenko.bybit.client.model.impl.ws.Ticker;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public interface BybitStreamClient {

    Mono<Void> subscribeToOrderBookStream(
            Consumer<BybitWsResponse<Orderbook>> processor,
            Map<String, OrderbookStreamDepth> symbols
    );

    Mono<Void> subscribeToKlineStream(
            Consumer<BybitWsResponse<List<Kline>>> processor,
            Map<String, Interval> symbols
    );

    Mono<Void> subscribeToTickerStream(
            Consumer<BybitWsResponse<Ticker>> processor,
            Set<String> symbols
    );
}
