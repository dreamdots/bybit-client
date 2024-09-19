package ru.adotsenko.bybit.client.model.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    @JsonProperty("spot")
    SPOT("spot"),
    @JsonProperty("linear")
    LINEAR("linear"),
    @JsonProperty("inverse")
    INVERSE("inverse"),
    @JsonProperty("option")
    OPTION("option");

    private final String category;
}
