### Thread-safe의 의미는?

[출처 블로그](https://developer-ellen.tistory.com/205)

Thread-safe는 멀티스레드 환경에서 쓰인다. 멀티스레드 환경에서 어떤 변수, 함수, 객체에 여러 스레드가 접근해도 프로그램의 실행에 문제가 없음을 의미한다.<br>
쉽게 말해서, 멀티스레드로 동일 테스트를 여러번 해도 동일한 결과를 산출하는 프로그램의 안정성이라고 이해하면 되겠다.

그렇다면 어떻게 코딩해야 Thread-safe한 프로그램을 만들 수 있을까? 참고한 자료에서는 4가지 방법을 제시한다.
1. 상호 배제
2. 원자 연산
3. 스레드 로컬 저장소
4. 재진입

### 1. 상호배제(Mutual Exclusion)
공유 자원에 하나의 스레드만 접근할 수 있도록 통제하는 것이다. 즉, 세마포어/뮤텍스로 해당 자원에 락을 건다고 생각하면 되겠다.<br>
세마포어와 뮤텍스는 무엇인가?
1) 세마포어 : 뮤텍스의 상위 개념으로, n개의 공유자원에 대해 접근을 제한하는 기법(락을 거는 것)
2) 뮤텍스 : 1개의 공유자원에 대해 접근을 제한하는 기법 / 세마포어로 1개 자원에 대해 통제하는 것과 유사

### 2. 원자 연산(Atomic Operation)
원자 연산이란 한 연산에 한번의 행위만 수행되는 연산을 의미한다. 두 가지 예를 들어보자.
```java
int sum = 0
sum++; // 원자연산 아님

AtomicInteger sum = new AtomicInteger(0);
sum.incrementAndGet(); // 원자연산임
```
두 연산 모두 sum에 1을 더하는 하나의 연산이지만 로직에 차이가 있다.<br>
첫번째 연산은 sum을 메모리에서 꺼내옴 -> sum에 1을 더함 -> 다시 메모리에 저장함 이라는 3가지 행위가 이루어지고, 두번째 연산은 sum에 1을 더하는 행위만 이루어진다.<br>
이 둘의 차이는 다음을 확인해봐도 알 수 있다.

```java
import java.util.concurrent.atomic.AtomicInteger;

public static void main(String[] args) {
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

public class AtomicOperation {

    private int count = 0;

    public void increment() {
        count++;
        //count += 1;
    }

    public int getCount() {
        return count;
    }
}

public class AtomicOperation {

    private AtomicInteger count = 0;

    public void increment() {
        count.incrementAndGet();
        //count += 1;
    }

    public int getCount() {
        return count.get();
    }
}
```
여러번 코드를 실행해보면 ```count++```를 할 경우 결과가 1,2,3이 고루 나오지 않을 수 있고, ```AtomicInteger```를 할 경우 결과가 1,2,3이 고루 나옴이 보장된다.

### 3. 스레드 로컬 저장소(thread local storage)
공유자원을 최대한 줄이고 각각의 스레드만 접근 가능한 저장소를 사용함으로서 동시접근을 막는 방법이다. 전역 변수 사용에 유의하라고 해석하면 된다.

### 4. 재진입성(re-entrancy)
스레드 호출과 상관없이 프로그램에 문제가 없도록 작성해야 한다.

-----
그렇다면 자바에서는 어떻게 위 유의사항을 잘 지킬 수 있을까?
1. ```java.util.concurrent``` 패키지의 하위 클래스를 사용한다.(ex)Map 사용 시 ```ConcurrentMap``` 사용)
2. 싱글톤 패턴 사용
3. 동기화 블록에서 연산을 수행
4. 인스턴스 변수를 두지 않는다.