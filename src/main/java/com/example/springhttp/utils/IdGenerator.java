package com.example.springhttp.utils;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

    private static AtomicLong atomicLong = new AtomicLong(0);

    private IdGenerator() {

    }

    public static long nextId() {
        return atomicLong.getAndIncrement();
    }
}
