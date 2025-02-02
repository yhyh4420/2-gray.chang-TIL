### DI(Dependency Injection)이란? (인프런 강의 '스프링 핵심 원리-기본편' 참고)
의존 관계에 대해 먼저 생각해보자. 의존이란 쉽게 말해 '어떤 객체가 다른 객체를 참조함' 정도로 이해하면 되겠다.<br>
즉, 파라미터나 리턴값, 지역변수 등으로 다른 객체를 참조하는 것을 의미한다.<br>
객체지향 원칙의 마지막인 의존 역전의 원칙에 따라 의존은 추상화에 의존해야지 구체화에 의존하면 안된다. 즉, 구현을 위해 만들어놓은 interface나 abstract class에 의존해야 하지 그것들의 자식클래스에 직접 의존하면 안된다는 뜻이다.<br>
어플리케이션의 실행 시점에 실제 구현 객체를 생성하고 클라이언트에 전달해 클라이언트와 서버의 실제 의존관계가 연결되는 것을 의존관계 주입(DI, Dependency Injection)이라고 한다.<br>
그 중 AppConfig파일과 같이 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을 DI Container(또는 IOC 컨테이너)라고 한다.<br>

Appconfig 파일 예시
```java
public class AppConfig {

    // MemberServiceImpl이라는 구현체에 MemoryMemberRepository라는 구현체를 주입하는 MemberService 객체를 생성한다
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }  

    // OrderServiceImpl이라는 구현체에 MemoryRepository, FixDiscountPolicy라는 구현체를 주입하는 OrderService 객체를 생성한다.
    public OrderService orderService(){
        return new OrderServiceImpl(
                new MemoryMemberRepository(),
                new FixDiscoutPolicy());
    }
}
```

이 AppConfig파일을 스프링에서는 더 쉽게 관리한다.
```java
@Configuration
public class AppConfig {
    
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
        memberRepository(),
        discountPolicy());
    }
    
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}

```
순수 자바와 스프링으로 만든 DI 컨테이너 파일은 그 목적이 동일하다. 결국 구현체에 의존관계를 더 쉽게 주입하기 위한 별도의 파일이고, 런타임과 동시에 생성된다는 사실만 기억하면 된다.
