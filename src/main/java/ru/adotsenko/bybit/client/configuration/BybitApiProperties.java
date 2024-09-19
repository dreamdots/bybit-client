package ru.adotsenko.bybit.client.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ru.adotsenko.bybit.client")
public class BybitApiProperties {
    private boolean enabled = true;
    private boolean wsEnabled = false;
    private String token;
    private Mode mode = Mode.TEST;

    public enum Mode {
        PROD,
        TEST
    }
}
