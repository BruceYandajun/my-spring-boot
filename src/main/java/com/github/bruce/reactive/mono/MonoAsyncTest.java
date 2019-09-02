package com.github.bruce.reactive.mono;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple4;

@Slf4j
public class MonoAsyncTest {

    public static void main(String[] args) {
        StopWatch watch = new StopWatch("mono");
        watch.start();
        Mono<String> mono1 = Mono.fromSupplier(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "a";
        }).subscribeOn(Schedulers.elastic());
        Mono<String> mono2 = Mono.fromSupplier(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "b";
        }).subscribeOn(Schedulers.elastic());
//        watch.start("mono3");
        Mono<String> mono3 = Mono.fromSupplier(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "c";
        }).subscribeOn(Schedulers.elastic());
//        watch.start("mono4");
        Mono<String> mono4 = Mono.fromSupplier(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "d";
        }).subscribeOn(Schedulers.elastic());

        watch.stop();
        watch.start("block");
        Tuple4<String, String, String, String> block = Mono.zip(mono1, mono2, mono3, mono4).block();
        System.out.println(block.getT1() + " " + block.getT2() + " " + block.getT3() + " " + block.getT4());
        watch.stop();
        log.info(watch.prettyPrint());
    }
}
