### Bean Container란? (인프런 강의 '스프링 핵심 원리-기본편' 참고)

스프링에서 annotation을 통해서든, AppConfig파일을 통해서든 결국 DI를 하기 위해 빈이라는 것이 등록된다.<br>
빈이라는 것은 뭔가? 먼저 스프링 제공 어노테이션으로 매핑한 AppConfig.java파일 먼저 살펴보자.

```java
@Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository())
    }
}
```
이렇게 ```@Bean```이라는 어노테이션을 붙인 메서드는 스프링 컨테이너에 스프링 빈으로 등록된다
* 스프링 빈이란? 스프링 컨테이너가 관리하는 자바 객체이며, 스프링 컨테이너에 등록되어 관리되는 재사용 가능한 컴포넌트이다.
두서가 없었는데, 이렇게 빈이 등록되는 곳을 컨테이너라고 한다. 컨테이너란 무엇인가?
* 