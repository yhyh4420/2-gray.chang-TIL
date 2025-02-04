package org.til.thread.atomic;

public class AtomicOperationThread implements Runnable {

    private AtomicOperation operation;

    public AtomicOperationThread(AtomicOperation operation) {
        this.operation = operation;
    }

    @Override
    public void run() {
        operation.increment();
        System.out.println("count = " + operation.getCount());
    }
}
