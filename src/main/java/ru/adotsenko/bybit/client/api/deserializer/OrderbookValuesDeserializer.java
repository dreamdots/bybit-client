package ru.adotsenko.bybit.client.api.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

@Slf4j
public class OrderbookValuesDeserializer extends JsonDeserializer<HashMap<BigDecimal, BigDecimal>> {
    @Override
    public HashMap<BigDecimal, BigDecimal> deserialize(JsonParser jsonParser,
                                                       DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        var map = new HashMap<BigDecimal, BigDecimal>();

        var node = jsonParser.getCodec().readTree(jsonParser);
        if (node.isArray()) {
            for (JsonNode n : (ArrayNode) node) {
                map.put(new BigDecimal(n.get(0).asText()), new BigDecimal(n.get(1).asText()));
            }
        }

        return map;
    }
}
