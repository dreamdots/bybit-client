package ru.adotsenko.bybit.client.model.impl.ws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.adotsenko.bybit.client.model.impl.Interval;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Kline {
    private Instant start;
    private Instant end;
    private Interval interval;
    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal volume;
    private BigDecimal turnover;
    private Boolean confirm;
    private Instant timestamp;
}
