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
* 스프링 빈이란? 스프링 컨테이너가 관리하는 자바 객체이며, 스프링 컨테이너에 등록되어 관리되는 재사용 가능한 컴포넌트이다.<br>

두서가 없었는데, 이렇게 빈이 등록되는 객체를 컨테이너라고 한다. 컨테이너란 무엇인가?
* 스프링 컨테이너는 스프링 프레임워크의 핵심 컴포넌트이며, 자바 객체의 생명주기를 관리하고 생성된 자바 객체에 추가적인 기능을 제공한다.
* 스프링 컨테이너는 위의 ```@Configuration```으로 명시된 파일을 ```ApplicationContext``` 클래스의 객체로 호출해줌으로서 생성된다.
* 쉽게 말해 ```ApplicationContext```객체가 컨테이너이다. 참고로 ```ApplicationContext```는 인터페이스이다.

스프링 컨테이너에 빈이 등록되는 과정
1. 스프링 컨테이너가 생성된다
    ```java
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class)
    ```
2. 스프링 컨테이너는 파라미터로 넘어온 설정 클래스 정보들을 사용하여 스프링 빈을 등록한다.
3. 설정 정보를 참고하여 의존관계가 주입된다. -> 단순 자바코드 호출과 유사해보이지만 싱글톤 패턴이라는 차이가 있다.