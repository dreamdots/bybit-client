package ru.adotsenko.bybit.client.model.impl.ws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Ticker {
    private String symbol;
    private TickDirection tickDirection;
    private BigDecimal price24hPcnt;
    private BigDecimal lastPrice;
    private BigDecimal prevPrice24h;
    private BigDecimal highPrice24h;
    private BigDecimal lowPrice24h;
    private BigDecimal prevPrice1h;
    private BigDecimal markPrice;
    private BigDecimal indexPrice;
    private BigDecimal openInterest;
    private BigDecimal openInterestValue;
    private BigDecimal turnover24h;
    private BigDecimal volume24h;
    private Instant nextFundingTime;
    private BigDecimal fundingRate;
    private BigDecimal bid1Price;
    private BigDecimal bid1Size;
    private BigDecimal ask1Price;
    private BigDecimal ask1Size;

    @Getter
    @RequiredArgsConstructor
    public enum TickDirection {
        @JsonProperty("PlusTick")
        PLUS_TICK,
        @JsonProperty("ZeroPlusTick")
        ZERO_PLUS_TICK,
        @JsonProperty("MinusTick")
        MINUS_TICK,
        @JsonProperty("ZeroMinusTick")
        ZERO_MINUS_TICK
    }
}
