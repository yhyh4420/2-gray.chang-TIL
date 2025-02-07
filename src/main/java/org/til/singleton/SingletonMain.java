package org.til.singleton;

public class SingletonMain {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        callNormalSingleton();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("totalTime = " + totalTime + "ms");
        //BPSingleton();
    }

    private static void BPSingleton() throws InterruptedException {
        for(int i=0; i<10; i++) {
            BPSingletonThread bpSingletonThread = new BPSingletonThread();
            Thread thread = new Thread(bpSingletonThread);
            thread.start();
            Thread.sleep(1000);
        }
    }

    private static void callNormalSingleton() {
        for(int i=0; i<10000; i++) {
            SingletonNormalThread singletonNormalThread = new SingletonNormalThread();
            Thread thread = new Thread(singletonNormalThread);
            thread.start();
        }
    }
}