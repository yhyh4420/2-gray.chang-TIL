### 직렬화(Serialization)이란?
- 자바 언어에서 사용되는 객체나 데이터를 Byte Stream 형태로 연속적인 데이터로 변환하는 포맷 변환 기술이다.
  - Byte Stream
    - 자바에서는 파일이나 콘솔의 입출력을 직접 다루지 않고 대신 Stream이라는 흐름을 통해 다룬다
    - Stream이란 실제의 입력이나 출력이 표현된 데이터의 흐름을 의미
    - 전송 단위에 의해 문자스트림, 바이트스트림 등으로 나뉨
- JVM의 힙 또는 스택 메모리에 상주하고 있는 객체 데이터를 직렬화를 통해 영속적(persistent)으로 db나 파일에 저장하고, 다른 자바 프로그램에서 역직렬화를 통해 JVM 메모리에 적재한다.
- 자바 직렬화 사용처 : 서블릿 세션, 캐시, 자바 RMI
- 하지만 요즘은 JSON방식을 많이 사용하는데, 굳이 직렬화를 사용하는 이유는 무엇일까?
  - 직렬화의 장점
    1) 자바 고유의 기술인 만큼 자바에 최적화되어있다.
    2) 자바의 다양한 reference 타입에 무관하게 모두 공유 가능하다(객체 통째로 보내는게 가능)
  - 직렬화의 단점
    1) 용량이 크다. .ser파일은 json보다 용량이 두 배 이상 차이가 난다.
    2) 역직렬화는 보안에 위협적이다.
       - 역직렬화 과정에서 ```ObjectInputStream```의 ```readObject()```를 수행하게 되면 역직렬화 대상의 객체 그래프가 역직렬화되어 classpath 안의 모든 타입의 객체가 만들어지는데, 이 과정에서 객체 내 모든 로직을 수행할 수 있게 된다.
       - 객체를 직렬화해서 외부로 보내는 중에 누가 가로채어 파일 바이트 내용을 조작하면 공격이 가능하다.
- 자바 직렬화 사용법
  - 먼저 직렬화를 구현하고자 하는 객체는 ```Serializable```라는 인터페이스를 상속받아야 한다.
    ```java
       @Getter @Setter
       public class Service implements Serializable {
           private long id;
           private String name;
           private String password;
           private int age;

           public Service(long id, String name, String password, int age) {
               this.id = id;
               this.name = name;
               this.password = password;
               this.age = age;
           }

           @Override
           public String toString() {
               return "Service{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       ", password='" + password + '\'' +
                       ", age=" + age +
                       '}';
           }
    ```
    - 와! 그럼 ```Serializable``` 인터페이스는 대단한 기능이 있나보다! 한번 인터페이스를 열어보자.
    ```java
    public interface Serializable {
    } // 끝임. 뭐 없음.
    ```
    - ```Serializable```인터페이스는 마커 인터페이스이다. 쉽게 말해 이 객체를 직렬화할때 사용할겁니다 라고 표시하는 역할이고, 그 외에 다른 메서드는 없다. 다만 이 인터페이스를 상속하지 않은 객체를 직렬화하면 ```NoSerializableException``` 에러가 발생한다.
  - 앞에서 직렬화란 바이트 '스트림' 형태로 데이터를 변형시킨다고 했다. 따라서 ```ObjectOutputStream```을 사용하면 된다.
```java
public class SerializeMain {

  static String fileName = "src/main/java/org/til/serializable/service.ser";

  public static void main(String[] args) {
    serialization(fileName);
    deserialization(fileName); 
    // Service{id=1, name='gray', password='1q2w3e4r!', age=30}
  }

  private static void deserialization(String fileName) {
    try{
      FileInputStream fis = new FileInputStream(fileName);
      ObjectInputStream ois = new ObjectInputStream(fis);

      Service deserializedService = (Service) ois.readObject(); // 캐스팅 필요
      System.out.println(deserializedService);
    } catch (IOException | ClassNotFoundException e){
      e.printStackTrace();
    }
  }

  private static void serialization(String fileName) {
    Service service = new Service(1,"gray", "1q2w3e4r!", 30);
    try{
      FileOutputStream fos = new FileOutputStream(fileName);
      ObjectOutputStream oos = new ObjectOutputStream(fos);

      oos.writeObject(service);
    } catch (IOException e){
      e.printStackTrace();
    }
  }
}
```