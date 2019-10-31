package com.github.bruce.reactive.mono;

import reactor.core.publisher.Mono;

public class MonoOrTest {
    public static void main(String[] args) {
        Mono<Boolean> a = Mono.just(Boolean.FALSE);
        Mono<Boolean> b = Mono.just(Boolean.TRUE);
        Mono<Boolean> or = a.or(b);
        System.out.println(or.block());
    }
}
