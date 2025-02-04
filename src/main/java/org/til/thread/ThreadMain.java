package org.til.thread;

import org.til.thread.atomic.AtomicOperation;
import org.til.thread.atomic.AtomicOperationThread;

public class ThreadMain {
    public static void main(String[] args) throws InterruptedException {
        threadTest1();
        System.out.println("------------------------------");
        AtomicOperationTest();
    }

    private static void AtomicOperationTest() {
        AtomicOperation operation = new AtomicOperation();

        AtomicOperationThread atomicOperationThread = new AtomicOperationThread(operation);
        AtomicOperationThread atomicOperationThread2 = new AtomicOperationThread(operation);
        AtomicOperationThread atomicOperationThread3 = new AtomicOperationThread(operation);

        Thread thread1 = new Thread(atomicOperationThread);
        Thread thread2 = new Thread(atomicOperationThread2);
        Thread thread3 = new Thread(atomicOperationThread3);

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static void threadTest1() throws InterruptedException {
        Counter counter = new Counter();

        ConuterThread counterThread = new ConuterThread(counter);
        ConuterThread counterThread2 = new ConuterThread(counter);

        Thread thread1 = new Thread(counterThread);
        Thread thread2 = new Thread(counterThread2);

        long start = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        long end = System.currentTimeMillis();

        System.out.println("counterThread = " + counterThread.getCounter().getValue());
        System.out.println("counterThread2 = " + counterThread2.getCounter().getValue());
        System.out.println("time = " + (end - start));
    }
}
