[null 관련 참고블로그](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EA%B0%9C%EB%B0%9C%EC%9E%90%EB%93%A4%EC%9D%84-%EA%B4%B4%EB%A1%AD%ED%9E%88%EB%8A%94-NULL-%ED%8C%8C%ED%97%A4%EC%B9%98%EA%B8%B0)<br>
자바에서 null을 신경쓰는 이유와 어떻게 사용해야 하는지
### Null이란?
- 1965년 알골이라는 프로그래밍 언어를 만들다가 도입
- '값이 존재하지 않는 상황'을 편리하게 표현하기 위해 고안한 개념
- ```토니 호어, 'null은 10억 달러짜리 실수이다'```

### 자바에서 null이란?
- primitive 자료형에는 null을 넣을 수 없음
    - 0이나 공백으로 값이 없음을 간접적으로 표현하기 때문
- reference 자료형은 기본값을 null로 지정할 수 있음
    - 참고 : 각 자료형의 기본 타입

|       자료형        |   기본값    |
|:----------------:|:--------:|
|     boolean      |  false   |
|       char       | "\u0000" |
| byte, short, int |    0     |
|      float       |   0.0f   |
|      double      |   0.0    |
| reference value  |   null   |

### null의 정확한 의미
- null의 개념을 알기 위해서는 포인터를 알아야 한다.
- 포인터란 C언어에서 주로 사용되는 개념으로, 메모리를 가리키는 주소를 저장하는 변수이다.
- 포인터 변수를 선언하고 지정하지 않는다면 **'이 변수는 아무것도 참조하지 않았어요'** 라는 표현을 한다. 그것이 바로 null이다.
- 즉, null은 **주소값이 없는 것**을 말한다.

### null과 자바 reference 필드의 관계
- 자바에서는 개발 편의를 위해 포인터의 개념을 숨겼다. 자바의 reference타입은 메모리 주소값을 변경할 수 없는 포인터의 변수인 셈이다.
- reference type은 생성 시 객체의 주소가 결정되지 않음므로 default로 null을 갖게 된다.
- wrapper class라는 reference type이 있다. int를 Integer과 같이 객체처럼 쓸 수 있게 해주는 클래스인데, 이런 클래스에서 제공하는 auto boxing & auto unboxing 과정에서 null이 문제가 된다.
```java
Integer test = new Integer();
test = null // 여기까지는 에러 안남
int toPrimitiveType = test // auto unboxing을 하게 되는데 이 과정에서 오류 발생
```

### null과 static 키워드
```java
public class NullObject {

    public void hello() {
        System.out.println("hello");
    }

    public static void helloStatic() {
        System.out.println("hello2");
    }
}

public class Main{
    public static void main(String[] args) {
        NullObject test = null;
        
        test.hello(); // NPE 발생!
        test.helloStatic(); // 컴파일 시 경고는 해주지만 돌아감
    }
}
```
- 위와 같은 상황이 일어나는 이유는 무엇일까? 알다시피 static 메서드는 클래스에 속하므로 JVM에서 method area에 적재된다.
- 클래스를 통해 바로 호출되는 메서드이지, 객체를 통한 호출이 아니므로 null과 상관없이 문제없이 호출 가능하다.
- 다만 static은 혼동을 줄이기 위해 ```클래스명.메서드명()```의 방식으로 호출하는게 일반적이므로 그렇게 하자.

### null과 instanceOf
- instanceof 연산자는 비교 대상이 객체값이라고 이해하기 보다는 참조하고 있는 주소 안에 있는 값의 타입이다.
```java
public class main{
    public static void main(String[] args) {
        String testString = null;
        System.out.println(testString instanceof String); // null을 참조하므로 false
        
        testString = "test";
        System.out.println(testString instanceof String); // 이제 true 출력됨
    }
}
```

### NPE를 막는 방법 : Optional 객체
- Optional 클래스는 Wrapper 클래스의 일종이다.
- 앞서 설명했듯이, primitive 타입이더라도 wrapper 클래스로 감싼다면 객체이기 때문에 null이 가능하다.
- 다만, wrapper class이므로 박싱, 언박싱 로직이 필요할 수 있는데, 반복되는 로직에서는 성능저하로 이어질 수 있다.
- 따라서, '절대 여기에는 null이 들어올 수 없어!'라고 하면 굳이 Optional을 쓰지 않아도 될 것 같다.
- 다만, Optional을 쓰게 되면 NPE가 발생하지 않는다는 절대적인 장점이 있기 때문에 적당히 판단하고 사용하도록 하자.