package com.github.bruce.reactive.flux;

import com.github.bruce.utils.BaseUtil;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

import static com.github.bruce.utils.BaseUtil.line;

public class FluxBaseTest {

    public static void main(String[] args) throws InterruptedException {
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<Object> seq2 = Flux.fromIterable(iterable);

        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe(BaseUtil::line);

        Flux<Integer> intsWithError = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 2) {
                        return i;
                    }
                    throw new RuntimeException("Got to 3");
                });
        intsWithError.subscribe(i -> line( i + " in thread " + Thread.currentThread().getName()),
                error -> line("Error " + error),
                () -> line("Done"),
                sub -> sub.request(10));
    }
}
