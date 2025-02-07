package org.til.singleton;

public class SingletonNormalThread implements Runnable {

    @Override
    public void run() {
        SingletonNormal instance = SingletonNormal.getInstance();
        System.out.println("instance = " + instance);
    }
}
