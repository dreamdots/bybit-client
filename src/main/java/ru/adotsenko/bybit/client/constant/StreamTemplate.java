package ru.adotsenko.bybit.client.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamTemplate {
    public static final String ORDERBOOK_STREAM_TEMPLATE = "orderbook.%d.%s";
    public static final String KLINE_STREAM_TEMPLATE = "kline.%s.%s";
    public static final String TICKER_STREAM_TEMPLATE = "tickers.%s";
}
