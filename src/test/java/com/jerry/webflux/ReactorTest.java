package com.jerry.webflux;

import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author  LuoJianwei
 * @since   2020/6/11 22:23
 */
public class ReactorTest {
    @Test
    public void testReactor(){
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6);
        Mono<Integer> mono = Mono.just(1);
        Integer[] integers = {1, 2, 3, 4, 5, 6};
        Flux<Integer> arrayFlux = Flux.fromArray(integers);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Flux<Integer> listFlux = Flux.fromIterable(list);
        Flux<Integer> fluxflux = Flux.from(flux);
        Flux<Integer> streamFlux = Flux.fromStream(Stream.of(1, 2, 3, 4, 5, 6));

//        // 什么都不做
//        flux.subscribe();
//
//        // 打印每个元素
//        arrayFlux.subscribe(System.out::println);
//
//        // 打印每个元素，如果出错，输出错误信息。
//        listFlux.subscribe(System.out::println, System.err::println);
//
//        // 打印每个元素，如果出错，输出错误信息，并且在完成消费后输出 completed 。
//        fluxflux.subscribe(System.out::println, System.err::println, ()-> System.out.println("completed!"));
//
//        // 打印每个元素，如果出错，输出错误信息，并且在完成消费后输出 completed, 并且加入背压。
//        streamFlux.subscribe(System.out::println, System.err::println,
//                ()-> System.out.println("completed!"),
//                subscription -> subscription.request(3));
//
//        streamFlux.subscribe(new DemoSubscriber());

//        System.out.println("1---------------------");
//        flux.map(i -> i*3).subscribe(System.out::println);
//        System.out.println("2---------------------");
//        arrayFlux.flatMap(i->flux).subscribe(System.out::println);
//        System.out.println("3---------------------");
//        listFlux.filter(i -> i > 3).subscribe(System.out::println);
//        System.out.println("4---------------------");
//        Flux.zip(fluxflux, streamFlux).subscribe(zip-> System.out.println(zip.getT1() + zip.getT2()));

        flux.map(i -> {
            System.out.println(Thread.currentThread().getName() + "-map1");
            return i * 3;
        }).publishOn(Schedulers.elastic()).map(i->{
            System.out.println(Thread.currentThread().getName() + "-map2");
            return i/3;
        }).subscribeOn(Schedulers.parallel()).subscribe(it -> System.out.println(Thread.currentThread().getName() + "-map3"));
        while (true){}
    }

    static class DemoSubscriber extends BaseSubscriber<Integer>{
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("hookOnSubscribe");
            subscription.request(1);
        }

        @Override
        protected void hookOnNext(Integer value) {
            if (value == 4) {
                cancel();
            }
            System.out.println(value);
            request(1);
        }
    }
}
