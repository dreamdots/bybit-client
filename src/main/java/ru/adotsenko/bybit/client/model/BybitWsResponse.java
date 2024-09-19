package ru.adotsenko.bybit.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class BybitWsResponse<T> {
    @JsonProperty("ts")
    private Instant timestamp;
    private T data;
    private Type type;
    private String topic;
    @JsonProperty("conn_id")
    private String connectionId;
    @JsonProperty("req_id")
    private String requestId;
    @JsonProperty("ret_msg")
    private String retMsg;
    private Boolean success = true;

    @Getter
    @RequiredArgsConstructor
    public enum Type {
        @JsonProperty("snapshot")
        SNAPSHOT("snapshot"),
        @JsonProperty("delta")
        DELTA("delta");

        private final String type;
    }
}
