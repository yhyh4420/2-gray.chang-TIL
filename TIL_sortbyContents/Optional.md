[Optional 공식 문서](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)

```public final class Optional<T> extends Object```로 공식문서는 시작한다.
final class, 즉 불변객체이고, extends Object, 즉 Object라는 최상위 클래스에서 상속받는 객체라고 이해하고 시작하자.<br>
A container object which may or may not contain a non-null value라고 하고 있다.<br>
즉 값이 있을 수도 있고 없을 수도 있는 객체를 감싸는 Wrapper Class이다. null이 올 수 있는 값을 감싸기 때문에 NullPointException이 발생하지 않는다.<br>
다시 말해 Optional을 사용하는 이유는 값이 없는 경우를 표현하기 위함이라고 이해하면 된다.

```java
import java.util.Optional;

Class OptionalTest{

public static void main(String[] args) {
    Member member = null;
    Optional<Member> optionalMember = Optional.ofNullable(member);
    System.out.println(optionalMember.isPresent()); // false

    Optional<Member> optionalMember2 = Optional.of(new Member("test1", 10));
    /*
    Optional.of 메서드는 지정할 값이 절대 null이 아닐 경우 사용
    만약 null이 아님을 단언할 수 없다면 Optional.ofNullable() 메서드 사용
     */
    System.out.println(optionalMember2.get().getName()); // test1
    }
}
```
Optional 객체는 클래스 내부에서 처음부터 Empty객체를 가지고 있다.
```java
public final class Optional<T> {
    /**
     * Common instance for {@code empty()}.
     */
    private static final Optional<?> EMPTY = new Optional<>(null);
```

Optional 구현 클래스를 보면 Wrapper Class답게 다양한 메서드를 지원한다.<br>

```java
import java.util.Optional;

public static void main(String[] args) {
    Optional<String> test = Optional.ofNullable("hello!");
    Optional<String> test2 = Optional.of("hello!");
    /*
    둘이 동일한 내용이다. 다만 파라미터로 전달되는 객체가 null임을 단언할 수 없다면 ofNullable을 사용하자.
     */

    test.ifPresentOrElse(
            value -> system.out.println("값이 있습니다"), 
            () -> system.out.println("값이 없습니다."));
    /*
    Optional 내 값이 있는지 없는지에 따라 다른 연산 수행
     */
    
    test.filter(value -> value.contains("hello"))
            .ifPresentOrElse(
                    value -> System.out.println("hello를 포함합니다." + value),
                    () -> System.out.println("hello를 포함하지 않습니다."));
}
```

이처럼 람다식과 적절히 섞어 사용한다면 null에 대해 효과적으로 대응하면서 NPE를 발생시키지 않을 수 있다. 다만 Wrapper Class이므로 만약 객체가 앞으로도 null이 아니라고 확신하다면 굳이 쓰지 않아도 되지 않을까 싶다.

