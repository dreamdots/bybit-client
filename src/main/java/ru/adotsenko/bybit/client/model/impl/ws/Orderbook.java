package ru.adotsenko.bybit.client.model.impl.ws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.adotsenko.bybit.client.api.deserializer.OrderbookValuesDeserializer;

import java.math.BigDecimal;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Orderbook {
    @JsonProperty("s")
    private String figi;
    private Long cts;
    @JsonProperty("seq")
    private Long crossSequence;
    @JsonProperty("u")
    private Long updateId;
    @JsonProperty("b")
    @JsonDeserialize(using = OrderbookValuesDeserializer.class)
    private Map<BigDecimal, BigDecimal> bids;
    @JsonProperty("a")
    @JsonDeserialize(using = OrderbookValuesDeserializer.class)
    private Map<BigDecimal, BigDecimal> asks;
}
