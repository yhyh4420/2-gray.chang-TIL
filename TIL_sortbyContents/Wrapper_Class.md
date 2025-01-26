[참고 블로그](https://inpa.tistory.com/entry/JAVA-%E2%98%95-wrapper-class-Boxing-UnBoxing)

자료형은 기본적으로 두가지 유형으로 나뉜다는 사실은 이제 알 것이다.
1. 원시 타입(aka 기본타입, primitive type...)
2. 참조 타입(aka reference type)

그런데 원시 타입의 자료들을 그대로 쓰지 못하는 경우가 종종 있다. 파라미터가 객체로 전송되는 경우가 그 예시인데, 이럴 경우 우리는 원시 타입을 객체 형태로 감싼다. 이 과정을 Boxing이라고 하고, 이러한 클래스들을 Wrapper class라고 한다.

Wrapper class 종류

|  기본 타입  |  래퍼 클래스   | 비고 |
|:-------:|:---------:|:--:
|  byte   |   Byte    | 정수 |
|  short  |   Short   | 정수 |
|   int   |  Integer  | 정수 |
|  long   |   Long    | 정수 |
|  float  |   Float   | 실수 |
| double  |  Double   | 실수 |
|  char   | Character | 문자 |
| boolean |  Boolean  | 진위 |

예시
```java
Integer int1 = new Integer(1); // 1이라는 정수를 래퍼클래스에 넣는 이 과정을 박싱(Boxing)이라고 한다.
Integer int2 = 1; // 이렇게도 가능하다. 이걸 오토박싱이라고 한다.
```

내부의 값을 꺼내는 방법은 다음과 같다. 통상 ```~~Value()```의 형태로 꺼낸다.
```java
Integer int1 = 1;
int int2 = int1.intValue(); // 래퍼클래스인 int1에서 1 꺼내져서 저장됨
```

이 박싱과 언박싱 과정이 귀찮기 때문에, 현재 자바는 오토박싱(auto boxing)과 오토언박싱(auto unboxing)을 지원한다.
```java
Integer int1 = 10; // auto boxing
int n = int1; // auto unboxing
```

래퍼클래스 연산은 일반 연산과 유사하나, 값을 비교할 때는 유의해야 한다.<br>
클래스로 박싱되어있기 때문에 ==연산자의 경우 메모리 주소값을 비교하게 된다.<br>
이때는 값을 꺼내서 ==로 비교하거나, .equals을 사용한다.

```java
Integer int1 = 10;
Integer int2 = 10;

system.out.println(int1 == int2) //false
system.out.println(int1.equals(int2)) //true
system.out.println(int1.intValue() == int2.intValue()); // true
```

이 오토언박싱은 주의해야 하는게, 어플리케이션 성능을 저하시킬 수 있다.<br>
당연한 것이 오토언박싱을 하기 위해 클래스에서 값을 꺼내서 다시 메모리에 할당하는 과정이 생기기 때문이다.<br>
실험해보면 다음 결과가 나온다.
```java
public class WrapperMain {
    public static void main(String[] args) {
        /*
        Wrapper class의 auto unboxing 성능 테스트
        1억번 더하는 연산으로 비교
         */

        withoutWrapper();
        withWrapper();
    }

    public static void withoutWrapper() {
        long start = System.currentTimeMillis();

        long sum = 0;
        for (long i = 0; i < 100000000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("without Wrapper class : " + (end - start) + "ms");
    }

    public static void withWrapper() {
        long start = System.currentTimeMillis();

        Long sum = 0L;
        for (long i = 0; i < 100000000; i++) {
            sum += i; // i는 primitive type이므로 sum과 연산하기 위해 sum을 오토 언박싱 해주고 다시 박싱함.
        }
        long end = System.currentTimeMillis();
        System.out.println("with Wrapper class, auto Unboxing : " + (end - start) + "ms");
    }
}
```
| 원시자료 사용 | 오토언박싱 사용 |
|:-------:|:--------:|
|  35ms   |  177ms   |

만약 정말 대용량 트래픽 발생의 경우 이 5배가 넘는 성능차이는 치명적일 수 밖에 없다. 잘 생각하고 사용하자.