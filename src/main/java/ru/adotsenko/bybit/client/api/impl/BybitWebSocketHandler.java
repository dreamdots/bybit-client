package ru.adotsenko.bybit.client.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.adotsenko.bybit.client.model.BybitWsResponse;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
public class BybitWebSocketHandler<T, R extends BybitWsResponse<T>> implements WebSocketHandler {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final Function<String, R> mapper;
    private final Consumer<R> processor;
    private final BybitStreamClientImpl.SubscribeRequest subscribeRequest;

    @SuppressWarnings("NullableProblems")
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session
                .send(Mono.fromCallable(() -> session.textMessage(OBJECT_MAPPER.writeValueAsString(subscribeRequest))))
                .doOnSubscribe(s -> log.info("Send subscription msg {}", subscribeRequest))
                .thenMany(session.receive())
                .doOnNext(msg -> log.info("Received msg {}", msg.getPayloadAsText()))
                .map(msg -> mapper.apply(msg.getPayloadAsText()))
                .doOnNext(msg -> {
                    if (!Boolean.TRUE.equals(msg.getSuccess())) {
                        throw new RuntimeException(msg.getRetMsg());
                    }
                })
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(processor)
                .onErrorContinue((t, o) -> log.error("Exception while processing websocket msg", t))
                .then();
    }
}
