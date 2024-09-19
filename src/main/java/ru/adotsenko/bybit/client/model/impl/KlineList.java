package ru.adotsenko.bybit.client.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class KlineList {
    private Category category;
    private String symbol;
    private List<Kline> list;
}
