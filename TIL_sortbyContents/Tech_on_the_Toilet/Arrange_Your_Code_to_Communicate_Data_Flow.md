출처 : [google Testing Blog](https://testing.googleblog.com/)

주된 내용은 데이터 흐름이 잘 보이게 코드를 작성하자는 말이다.(공부하면서 번역하는거라 다소 의역 있음)
* 우리는 코드를 한줄씩 차례대로 읽는다.
* 코드를 읽는 사람의 인지 부하를 줄이고 이해하기 쉽게 만들기 위해서는 인접한 코드가 응집해야 한다.(연관이 있어야 한다.)
* 이런 목표를 달성하기 위해서는 메서드 안의 데이터 흐름과 코드 라인을 맞추는 것이다.
* 다음 코드는 좋은 코드와 나쁜 코드의 예시이다._(본래 예시는 코틀린이지만 자바로 작성하겠다.)_
```java
//나쁜 코드
public class SandwichMaker {

    public Sandwich getSandwich(Bread bread, Pasture pasture) {
        // 우유와 관련된 코드, 빵과 관련된 코드가 번갈아가면서 나타난다.
        Cow cow = pasture.getCow();
        Bread slicedBread = bread.slice();
        Milk milk = cow.getMilk();
        Toast toast = toastBread(slicedBread);
        Cheese cheese = makeCheese(milk);

        return new Sandwich(cheese, toast);
    }

    private Toast toastBread(Bread bread) {
        System.out.println("빵을 토스트로 바꾸는 로직");
        return new Toast(bread);
    }

    private Cheese makeCheese(Milk milk) {
        System.out.println("우유로 치즈를 만드는 로직");
        return new Cheese(milk);
    }
}
```
```java
//좋은 코드
public class SandwichMaker {
    
    public Sandwitch getSandwich(Bread bread, Pasture pasture) {
        // 소-> 우유 -> 치즈 로직
        Cow cow = pasture.getCow();
        Milk milk = cow.getMilk();
        Cheese cheese = makeCheese(milk);
        
        // 빵 -> 자른빵 -> 토스트 로직
        Bread sliceBread = bread.slice();
        Toast toast = toastBread(sliceBread);
        
        return new Sandwich(cheese, toast)
    }
    
    // 이하 toastBread, makeChese 로직 동일
}
```
* 시각적으로 관련된 라인을 강조하기 위해서 각각의 코드블럭에 빈칸을 더해도 좋다.

* 메서드를 추출하는 방식을 통해 가독성을 증가시킬 수 있다.(e.g., 좋은 코드의 첫 세줄을 ```makeCheese()```메서드로 분리)
* 하지만 어떤 시나리오에서 메서드를 추출하는것은 도움되지 않는다.(e.g., 데이터가 로깅을 위해 두 번 사용되는 등)
* 그러한 라인들을 데이터 흐름과 맞추게 정렬한다면 코드 명쾌함을 높일 수 있다.
```java
public class SandwichMaker{
    
    pulbic Sandwich getSandwich(Bread bread, Pasture pasture){
        Cow cow = pasture.getCow();
        Milk milk = cow.getMilk();
        //여기서 로깅으로 cow와 milk가 사용되므로 메서드 분리하기 힘들다.
        reportFatContentToStreamz(cow.getAge(), milk);
        Cheese cheese = makeCheese(milk);
        
        Bread sliceBread = bread.slice();
        Toast toast = toastBread(sliceBread);
        //여기서도 로깅으로 데이터들이 사용되므로 메서드 분리가 힘들다.
        logWarningIfAnyExpired(bread, toast, milk, cheese);
        return Sandwich(cheese, toast);
    }
}
```
* 더 복잡한 데이터 흐름이어서 변수들을 묶는것이 완벽하지 않을 수 있다. 그래도 위 방향으로 점진적으로 바꾸는 것만으로도 코드의 가독성을 높여준다.
* 변수 선언은 항상 사용하는 시점과 가까이 붙이는 것이 좋은 시작점이다.