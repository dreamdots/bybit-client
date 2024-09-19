package ru.adotsenko.bybit.client.constant;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.experimental.UtilityClass;
import ru.adotsenko.bybit.client.model.BybitWsResponse;
import ru.adotsenko.bybit.client.model.impl.ws.Kline;
import ru.adotsenko.bybit.client.model.impl.ws.Orderbook;
import ru.adotsenko.bybit.client.model.impl.ws.Ticker;

import java.util.List;

@UtilityClass
public class TypeReferenceFactory {
    public static final TypeReference<BybitWsResponse<Orderbook>> ORDERBOOK_TYPEREF
            = new TypeReference<BybitWsResponse<Orderbook>>() {
    };

    public static final TypeReference<BybitWsResponse<List<Kline>>> KLINE_TYPEREF
            = new TypeReference<BybitWsResponse<List<Kline>>>() {
    };

    public static final TypeReference<BybitWsResponse<Ticker>> TICKER_TYPEREF
            = new TypeReference<BybitWsResponse<Ticker>>() {
    };
}
