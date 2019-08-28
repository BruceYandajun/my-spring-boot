package com.github.bruce.reactive.mono;

import reactor.core.publisher.Mono;

public class MonoBaseTest {
    public static void main(String[] args) throws InterruptedException {
        Mono<String> empty = Mono.empty();
        Mono<String> foo = Mono.just("foo");

        final Mono<String> mono = Mono.just("hello ");
        new Thread(() -> mono
                .map(msg -> msg + "thread ")
                .subscribe(v ->
                        System.out.println(v + Thread.currentThread().getName())
                )
        ).join();

    }
}
