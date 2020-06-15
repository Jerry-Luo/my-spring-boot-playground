package com.jerry.webflux.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.annotation.Resource;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Resource
    private DemoHandler demoHandler;

    @Bean
    public RouterFunction<ServerResponse> demoRouter(){
        return route(GET("hellow"), demoHandler::hello)
                .andRoute(GET("worldw"), demoHandler::world)
                .andRoute(GET("worldw"), demoHandler::times);
    }
}
