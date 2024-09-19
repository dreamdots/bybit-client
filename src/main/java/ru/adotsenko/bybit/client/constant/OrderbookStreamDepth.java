package ru.adotsenko.bybit.client.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderbookStreamDepth {
    _1(1),
    _50(50),
    _200(200);

    private final int depth;
}
