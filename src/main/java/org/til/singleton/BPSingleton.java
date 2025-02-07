package org.til.singleton;

public class BPSingleton {
    private BPSingleton() {}

    private static class SingletonHolder {
        private static final BPSingleton INSTANCE = new BPSingleton();
    }

    public static BPSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
