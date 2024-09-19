package ru.adotsenko.bybit.client.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class ServerTime {
    private Long timeSecond;
    private Long timeNano;
}
