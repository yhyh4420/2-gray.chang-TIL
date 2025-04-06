package org.til.proxy;

public class ProxyMain {
    public static void main(String[] args) {
        Service serviceProxy = new ServiceProxy(new ServiceImpl(), "admin");
        serviceProxy.action();
        System.out.println();

        Service serviceProxy1 = new ServiceProxy(new ServiceImpl(), "guest");
        serviceProxy1.action();
    }
}
