## Thread-safe한 Singleton 만들기
thread-safe한 설계를 위해서는 싱글톤 패턴으로 스레드를 만들어야 한다고 이전에 언급한 바 있다.[(이전 공부내용)](TIL_sortbyContents/Thread_safe.md)<br>
그렇다면 어떻게 싱글톤 패턴을 만들어야 할까? 먼저 일반 싱글톤 패턴 스레드를을 보자.
```java
public class SingletonNormalThread implements Runnable {

    @Override
    public void run() {
        SingletonNormal instance = SingletonNormal.getInstance();
        System.out.println("instance = " + instance);
    }
}

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
```
이 상태에서 스레드를 생성하면서 주소를 확인해보면 이상한 점을 확인할 수 있다.
```java
public class SingletonMain {
    public static void main(String[] args) throws InterruptedException {
        callNormalSingleton();
    }

    private static void callNormalSingleton() {
        for(int i=0; i<10; i++) {
            SingletonNormalThread singletonNormalThread = new SingletonNormalThread();
            Thread thread = new Thread(singletonNormalThread);
            thread.start();
        }
    }
}
```
결과를 보면 다른 주소값이 나오는 것을 확인할 수 있다. 한 객체만 생성해서 돌려쓰는 것이 싱글톤인데 이러면 더이상 thread-safe하지 않다.
```
...
instance = org.til.singleton.SingletonNormal@31f69f18
instance = org.til.singleton.SingletonNormal@31f69f18
instance = org.til.singleton.SingletonNormal@7c6bb0c7 //주소값이 다른 객체가 튀어나온다
...
```

### 방법 1. synchronized 메서드 선언
```java
public class SingletonNormal {
    private static SingletonNormal singletonNormal;

    private SingletonNormal() {}

    public static synchronized SingletonNormal getInstance() { // synchronized 메서드를 추가
        if (singletonNormal == null) {
            singletonNormal = new SingletonNormal();
        }
        return singletonNormal;
    }
}
```
이렇게 되면 runnable 객체의 ```run()```메서드에서 호출하는 ```getInstance()```메서드에 락이 걸리게 된다. 즉, 한 스레드가 ```getInstance()```를 완료할 때까지 다른 스레드는 이 메서드를 수행할 수 없다.<br>
당연히 단점이 있다. 다른 스레드가 로직을 수행 못하게 강제하니, 성능이 떨어질 수 밖에 없다. 그리고 만약 이미 인스턴스가 생긴 이후라면 이미 생성된 인스턴스를 반환하므로 무의미하다.

### 2. DCL(Double Check Locking) 방식
```java
public class SingletonNormal {
    private static SingletonNormal singletonNormal;

    private SingletonNormal() {}

    public static SingletonNormal getInstance() {
        if (singletonNormal == null) {
            synchronized (SingletonNormal.class) { // 더블체크 로직 추가
                if (singletonNormal == null) {
                    singletonNormal = new SingletonNormal();
                }
            }
        }
        return singletonNormal;
    }
}
```
* DCL이란? : 인스턴스가 생성되지 않았을 때만 락을 거는 방식, CPU 캐시에 값을 먼저 쓰고 메인메모리로 flush시 getInstance 값이 불일치할 수 있다.

### 3. DCL + volatile 키워드
```java
public class SingletonNormal {
    private static volatile SingletonNormal singletonNormal; // volatile 키워드 추가

    private SingletonNormal() {}

    public static SingletonNormal getInstance() {
        if (singletonNormal == null) {
            synchronized (SingletonNormal.class) {
                if (singletonNormal == null) {
                    singletonNormal = new SingletonNormal();
                }
            }
        }
        return singletonNormal;
    }
}
```
이렇게 하면 thread-safe하게 설계할 수 있지만, JDK 1.5 이상에서만 volatile 키워드를 지원하고, JVM에 대한 깊은 이해가 필요하기 때문에 사용을 지양한다.

### 4. Bill Pugh Singleton
```java
public class BPSingleton {
    private BPSingleton() {}

    private static class SingletonHolder {
        private static final BPSingleton INSTANCE = new BPSingleton();
    }

    public static BPSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
```
holder 역할을 하는 private static class를 inner class로 사용하는 것이다. 이 홀더는 static으로 메서드 영역에 할당되고, 내부의 인스턴스 변수도 final 키워드를 붙여 불변성을 보장한다.

### 5. ENUM
```java
public enum SetSingletom{
    INSTANCE;
}
```
간단하다. enum 클래스 자체가 싱글톤이기 때문에 간단하고 thread safe하지만, 나중에 유지보수 시 singleton을 해제하고 싶으면 코드를 다시 짜야 하고, 타 클래스에서 상속받기 힘들다.