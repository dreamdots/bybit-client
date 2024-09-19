package ru.adotsenko.bybit.client.model.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Interval {
    _1MIN("1"),
    _3MIN("3"),
    _5MIN("5"),
    _15MIN("15"),
    _30MIN("30"),
    _60MIN("60"),
    _120MIN("120"),
    _240MIN("240"),
    _360MIN("360"),
    _720MIN("720"),
    DAY("D"),
    WEEK("W"),
    MONTH("M");

    private final String interval;
}
