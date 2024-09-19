package ru.adotsenko.bybit.client.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.adotsenko.bybit.client.configuration.BybitApiProperties;

import java.net.URI;

@Getter
@RequiredArgsConstructor
public enum BybitUri {
    MAINNET_SPOT_PUBLIC("wss://stream.bybit.com/v5/public/spot"),
    MAINNET_PRIVATE("wss://stream.bybit.com/v5/private"),
    TESTNET_SPOT_PUBLIC("wss://stream-testnet.bybit.com/v5/public/spot"),
    TESTNET_PRIVATE("wss://stream-testnet.bybit.com/v5/private"),
    MAINNET_REST_API("https://api.bybit.com/"),
    TESTNET_REST_API("https://api-testnet.bybit.com/");

    private final String url;

    @SneakyThrows
    public static URI getPublicUri(BybitApiProperties.Mode mode) {
        switch (mode) {
            case PROD:
                return URI.create(MAINNET_SPOT_PUBLIC.getUrl());
            case TEST:
                return URI.create(TESTNET_SPOT_PUBLIC.getUrl());
            default:
                throw new IllegalArgumentException();
        }
    }

    @SneakyThrows
    public static URI getPrivateUri(BybitApiProperties.Mode mode) {
        switch (mode) {
            case PROD:
                return URI.create(MAINNET_PRIVATE.getUrl());
            case TEST:
                return URI.create(TESTNET_PRIVATE.getUrl());
            default:
                throw new IllegalArgumentException();
        }
    }

    @SneakyThrows
    public static URI getRestUri(BybitApiProperties.Mode mode) {
        switch (mode) {
            case PROD:
                return URI.create(MAINNET_REST_API.getUrl());
            case TEST:
                return URI.create(TESTNET_REST_API.getUrl());
            default:
                throw new IllegalArgumentException();
        }
    }
}
