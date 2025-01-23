## String

String은 객체이다. 뭔가 원시 자료형일 것 같지만 객체이고, 그렇기 때문에 String으로 다양한 연산을 수행할 수 있다.
```java
public static void main(String[] args) {
    String s = "abc";
    System.out.println(s[0]); // a
    System.out.println(s+"d"); // abcd
}
```
그런데 객체라고 하면 가지는 생각이 들기 마련이다. 우리가 흔히 객체를 다룰 때 쓰는 방식과 string을 쓸 때의 방식은 확연하게 다르다.
```java
//일반적인 객체
@Getter @Setter // 롬복 사용한다 가정, 참고로 이부분은 getter와 setter를 자동으로 만들어주는 어노테이션
public class Order{
    private String product;
    private int price;
    
    // 생성자, 참고로 이것도 위에 어노테이션으로 @AllArgsConstoructor 같은 걸로 자동으로 만들 수 있음
    public Order(String product, int price){
        this.product = product;
        this.price = price;
    }
}

public class Main{
    public static void main(String[] args) {
        Order order = new Order("hamburger", 10000);
        System.out.println(order.getProduct());
        //.....기타 로직
    }
}
```
그런데 우리가 String을 사용할 때는 별도 선언이나 객체 생성 없이 바로 문자열을 대입해버린다. 왜그럴까?<br>
String 객체의 주석을 보자. 생성자 부근에서 다음 문구를 확인할 수 있다.
```
Note that use of this constructor is unnecessary since Strings are immutable.
```
해석하자면, String은 immutable, 즉 불변하기 때문에 constructor가 불필요하다. 라는 뜻이다.(쓰지마..라는 뜻으로 이해하면 좋을 듯)<br>
String 객체는 생성되면 힙 영역 중 'string pool'이라는 곳에 별도로 저장되게 된다. 그곳에 string값이 저장되게 되는데, 각 변수들은 이 값의 주소값을 참조하게 된다.<br>
어느 블로그에 좋은 예시가 있어 이걸 참조하고자 한다.(출처 : https://ict-nroo.tistory.com/18)
```java
public static void main(String[] args) {
    String name1 = new String("name1");
    String name2 = "nameName";
    String name3 = "nameName";
}
```

여기서 ```String name1```객체는 새로운 객체를 만들어 힙 영역에 저장하고, ```name2```와 ```name3```은 힙 영역 내부의 String pool에 저장된 ```"nameName"```을 참조하는 주소값을 갖게 된다.<br>
즉, String 객체는 총 2개 만들어진다.

## StringBuffer, StringBuilder
StringBuffer, StringBuilder는 String이 불변이기 때문에 나타나는 불편함을 제거해준다.<br>
불편한게 뭐가 있을까? 문자열 처리가 그 예시일 수 있다.<br>
다음 예시를 보자.
```java
public static void main(String[] args) {
    String s = "abc";
    s.replace("a","d");
}
```
간단하게 문자열을 대체하는 메서드인데, 이럴 경우 string pool에는 "abc"와 "dbc" 둘 다 공존하게 된다. 이게 여러번 반복되게 되면 결국 메모리 낭비로 이어진다.<br>
StringBuffer, StringBuilder는 이 문제를 해결해준다. 이 객체들은 가변클래스이다. 즉, 본인 클래스의 상태를 변경하고 추가적인 메모리 소비는 없다.<br>
StringBuffer, StringBuilder 차이는 동기화 지원 유무이다. 멀티스레드일때는 동기화를 지원하는 StringBuffer, 단일 스레드 환경에서는 StringBuilder를 사용하면 된다.