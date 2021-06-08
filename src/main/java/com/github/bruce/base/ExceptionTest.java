package com.github.bruce.base;

import lombok.SneakyThrows;

public class ExceptionTest {
    public static void main(String[] args) {
        exception();
        System.out.println("after exception");
    }


    @SneakyThrows
    private static void exception() {
        throw new Exception("exception");
    }
}
