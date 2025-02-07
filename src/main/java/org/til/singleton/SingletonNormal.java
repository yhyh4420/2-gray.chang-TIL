package org.til.singleton;

public class SingletonNormal {
    private static SingletonNormal singletonNormal;

    private SingletonNormal() {}

    public static SingletonNormal getInstance() {
        if (singletonNormal == null) {
            singletonNormal = new SingletonNormal();
        }
        return singletonNormal;
    }
}