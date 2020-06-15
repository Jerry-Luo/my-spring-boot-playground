package com.jerry.webflux.controller;

import com.jerry.exception.BusinessException;
import com.jerry.exception.SystemException;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class DemoHandler {

    public Mono<ServerResponse> hello (ServerRequest request) {
        Optional<String> id = request.queryParam("id");

        int intId = id.map(Integer::parseInt).orElse(0);

        // 测试日志切面，及日志文件切割。
        // com.jerry.exception.ExceptionAspect
        // logback-spring.xml:74
        if (intId == 1) {
            MDC.put("bizType", "MONEY");
            throw new BusinessException("can not use this id " + id);
        }else if(intId == 2) {
            MDC.put("bizType", "HOUSE");
            throw new SystemException("can not use this id " + id);
        }

        return ok().contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("hello "), String.class);
    }

    public Mono<ServerResponse> world (ServerRequest request) {
        return ok().contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("world "), String.class);
    }

    public Mono<ServerResponse> times(ServerRequest request) {
//        return ok().contentType(MediaType.TEXT_EVENT_STREAM)
//                .body(Flux.interval(Duration.ofSeconds(1)))
//                .map(it-> DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return null;
    }
}
