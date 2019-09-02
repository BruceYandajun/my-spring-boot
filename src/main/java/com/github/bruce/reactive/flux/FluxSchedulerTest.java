package com.github.bruce.reactive.flux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.github.bruce.utils.BaseUtil.line;

@Slf4j
public class FluxSchedulerTest {
    public static void main(String[] args) {
        StopWatch watch = new StopWatch();
        watch.start("flux");
        List<Integer> list = new ArrayList<>();
        Flux.just("abc", "hello", "ddd")
                .flatMap(key -> get(key, list))
                .collectList().block();
        watch.stop();
        log.info(watch.prettyPrint());
        line(list);
    }

    private static Mono<List<Integer>> get(String key, List<Integer> list) {
        return Mono.fromCallable(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("get key: " + key + ", in thread " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Integer> integers = Collections.singletonList(key.hashCode());
            list.addAll(integers);
            return integers;
        }).subscribeOn(Schedulers.elastic());
    }
}
