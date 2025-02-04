package org.til.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private AtomicInteger value;

    public Counter() {
        this.value = new AtomicInteger(0);
    }

    public void increment() {
        value.incrementAndGet();
    }

    public int getValue() {
        return value.get();
    }
}
