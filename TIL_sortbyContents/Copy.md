자바의 복사에는 두가지가 있다. 얕은 복사와 깊은 복사가 있는데 이 둘의 차이를 알아보자.
### 얕은 복사(Shallow Copy)
얕은 복사는 주소값을 복사한다. 쉽게 말하자면, Heap memory에 저장된 값은 하나이고, Stack memory의 객체가 복사되어 같은 값을 참조하게 된다.<br>
그렇게 되면 복사된 객체에 어떤 로직을 수행해도 두 객체 모두 값이 변하게 된다. 예시를 들어보자.<br>

다음 객체가 있다고 가정하자.
```java
@Getter @Setter
public class Member {
    private String name;
    private int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```
이 객체를 가지고 다음 코드를 실행시키면 쉽게 이해가 가능하다.
```java
public class CopyMain {
    public static void main(String[] args) {
        Member member = new Member("gray", 100);
        Member member1 = member;
        member.setAge(20);
        System.out.println(member.toString()); // Member{name='gray', age=20}
        System.out.println(member1.toString()); // Member{name='gray', age=20}
    }
}
```
```member```의 값만 변경했을 뿐인데 얕은 복사를 한 ```member1```의 값도 변경되었다. 동일한 주소를 참조하고 있기 때문에 발생하는 현상이다.

### 깊은 복사(Deep Copy)
깊은 복사는 주소가 아닌 값을 복사한다. 즉, 객체를 깊은 복사하면 heap memory에 별도의 주소가 할당되고 거기에 동일한 값이 들어가게 된다.<br>
깊은 복사를 하기 위해서는 객체에 별도 메서드를 구현해줘야 한다.
```java
@Getter @Setter
public class Member {
    private String name;
    private int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // 복사하기 위한 복사 생성자를 추가한다.
    public Member(Member member){
        this.name = member.getName();
        this.age = member.getAge();
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```
복사 생성자를 사용해보자.
```java
public class CopyMain {
    public static void main(String[] args) {
        Member member = new Member("gray", 20)
        Member member2 = new Member(member);
        member2.setAge(5000);
        System.out.println(member.toString()); // Member{name='gray', age=20}
        System.out.println(member2.toString()); // Member{name='gray', age=5000}
    }
}
```
복사 생성자를 정의하여 파라미터로 복사하고자 하는 객체를 대입하면 깊은 복사를 할 수 있다.