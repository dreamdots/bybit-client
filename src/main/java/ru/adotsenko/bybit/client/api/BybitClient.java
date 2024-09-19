package ru.adotsenko.bybit.client.api;

import reactor.core.publisher.Mono;
import ru.adotsenko.bybit.client.model.impl.Interval;
import ru.adotsenko.bybit.client.model.BybitResponse;
import ru.adotsenko.bybit.client.model.impl.Category;
import ru.adotsenko.bybit.client.model.impl.KlineList;
import ru.adotsenko.bybit.client.model.impl.ServerTime;

public interface BybitClient {

    Mono<BybitResponse<ServerTime>> serverTime();

    Mono<BybitResponse<KlineList>> kline(Category category,
                                         String symbol,
                                         Interval interval,
                                         Long startTimestamp,
                                         Long endTimestamp,
                                         Integer limit
    );
}
