package com.jerry.webflux.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author  LuoJianwei
 * @since   2020/6/13 23:33
 */
@RestController
public class DemoController {

    @RequestMapping("/demo")
    public Mono<String> demo(){

        return Mono.just("demo");
    }
}
