package com.github.bruce.reactive.flux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.bruce.utils.BaseUtil.line;

@Slf4j
public class FluxSchedulerTest {
    public static void main(String[] args) {
        CountDownLatch runningCounter = new CountDownLatch(1);
        List<Thread> workers = Stream.generate(() -> new Thread(new Worker(runningCounter))).limit(10).collect(Collectors.toList());
        workers.forEach(Thread::start);
        runningCounter.countDown();

    }

    public static void test() {
//        StopWatch watch = new StopWatch("Watch");
//        watch.start("flux");
        List<Integer> list = new CopyOnWriteArrayList<>();
        List<Integer> block = Flux.just("abc", "hello", "ddd")
                .flatMap(key -> get(key, list)).collectList().block();
//        watch.stop();
//        System.out.println(watch.prettyPrint());
        line(list);
        line(block);
//        System.out.println("=================");
//        System.out.println("=================");
    }

    private static Mono<Integer> get(String key, List<Integer> list) {
        return Mono.fromCallable(() -> {
            try {
                Thread.sleep(2000);
//                System.out.println("get key: " + key + ", in thread " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(key.hashCode());
            return key.hashCode();
        }).subscribeOn(Schedulers.elastic());
    }

}

class Worker implements Runnable {

    private CountDownLatch runningCounter;

    Worker(CountDownLatch counter) {
        runningCounter = counter;
    }

    @Override
    public void run() {
        try {
            runningCounter.await();
            FluxSchedulerTest.test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
