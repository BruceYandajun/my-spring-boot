package com.github.bruce.reactive.flux;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
public class FluxGenerateTest {

    public static void main(String[] args) {
        Integer block = publish().bufferTimeout(1000, Duration.ofMillis(100))
                .flatMapIterable(Function.identity())
                .subscribeOn(Schedulers.fromExecutorService(Executors.newFixedThreadPool(2)))
                .flatMap(list -> Mono.fromSupplier(() -> consume(list)))
                .collectList()
                .map(sizeList -> sizeList.stream().mapToInt(Integer::intValue).sum()).block();
        log.info("block={}", block);

    }

    public static Flux<List<Long>> publish() {
        return Flux.generate(() -> 0L, (id, sink) -> {
//            try {
//                TimeUnit.MILLISECONDS.sleep(5000);
//                log.info("sleep");
//            } catch (InterruptedException e) {
//                log.error("sleep is error!!!", e);
//            }
            long nextId = 0L;
            List<Long> list = fill(id);
            log.info("list={}", list);
            if (list.size() > 0) {
                sink.next(list);
                nextId = list.get(list.size() - 1);
            } else {
                sink.complete();
            }
            return nextId;
        });
    }

    public static int consume(List<Long> list) {
       log.info("consume list={}", list);
       return list.size();
    }

    public static List<Long> fill(Long id) {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Long> list = Lists.newArrayList();
        int size = new Random().nextInt(10000);
        for (int i = 0; i < size; i ++) {
            list.add(Long.parseLong(new Random().nextInt(5) + ""));
        }
       return list;
    }

}
