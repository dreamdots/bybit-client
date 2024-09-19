package ru.adotsenko.bybit.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class BybitResponse<T> {
    private String retCode;
    private String retMsg;
    private T result;
    private Object retExtInfo;
    private Instant time;
}
