package org.til.thread;

public class ConuterThread implements Runnable {

    private Counter counter;

    public ConuterThread(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < 1000000; i++) {
            counter.increment();
        }
    }

    public Counter getCounter() {
        return counter;
    }
}
