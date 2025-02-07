package org.til.singleton;

public class BPSingletonThread implements Runnable {

    @Override
    public void run() {
        BPSingleton bpSingleton = BPSingleton.getInstance();
        System.out.println("bpsingleton = " + bpSingleton);
    }
}
