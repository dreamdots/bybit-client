package ru.adotsenko.bybit.client.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Kline {
    private Instant startTime;
    private BigDecimal openPrice;
    private BigDecimal highPrice;
    private BigDecimal lowPrice;
    private BigDecimal closePrice;
    private BigDecimal volume;
    private BigDecimal turnover;
}
