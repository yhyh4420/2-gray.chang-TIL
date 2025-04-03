출처 : [google Testing Blog](https://testing.googleblog.com/2025/01/arrange-your-code-to-communicate-data.html)

> 도메인 객체를 사용하면 변화에 대해 '회복탄력성'을 가지는 코드를 만들 수 있다는 요지이다. 즉, 도메인 객체를 사용하면 향후 기능 추가나 요구사항 변경 시 유연하게 대체가 가능하다는 말이다.(다소 의역있음)

- 제품의 요구사항이 많이 바뀌어도 본질적인 아이디어는 천천히 변화한다.
- 이것은 흥미로운 통찰로 이끈다 : 우리가 코드를 작성할 때 제품의 본질적인 아이디어에 부합하게 작성한다면, 향후의 프로덕트 변경에도 더 오래 살아남을 수 있다.
- 도메인 객체는 우리 코드에서 제품의 본질적인 아이디어와 매치되는 빌딩 블록이다.(class나 interface같은)
- 제품 요구사항의 '행동'에 초점을 맞추는 것 보다는 제품의 아이디어에 매치해야 한다.
<br><br>
- 예를 들어 당신이 배고픈 구글러들을 위해 맛있고 신선한 피자를 파는 gPizza팀의 일원이라고 가정하자.
- 인기가 많아져서 배달 서비스를 추가하고자 한다.
- 도메인 객체가 없으면 피자 배달 로직을 추가하는 가장 빠른 방법은 ```delivaryPizza```메서드를 만드는 것이다.
  - ````java
    public class DelivaryService{
        public void delivaryPizza(List<Pizza> pizzas){...}
    }
    ````
- 잘 작동하겠지만, 다른 음식을 추가한다면 어떻게 되겠는가? 또 코드를 써야 한다.
  - ````java
    public class DelivaryService{
        public void delivaryPizza(List<Pizza> pizzas, List<Drink> drinks){...}
    }
    ````
- 제품 종류가 많아진다면 점점 더 많은 메서드를 추가해야 할 것이다. 이런 반복적인 상황을 피하기 위해서는 어떻게 해야 할까?
- 제품의 요구사항 대신 제품 아이디어를 모델링한 도메인 객체를 추가하면 된다!
  - use case는 제품이 비즈니스 요구를 만족시키게 하는 행위이다.
  - 도메인 객체는 여러 유사한 use case에서 공통적으로 사용되는 아이디어를 나타낸다.
- 적절한 도메인 객체를 식별하기 위해서는 다음 질문을 해야 한다.:
  - 어떤 관련된 use case를 지원하는 제품이며, 향후 어떤 계획을 지원할 예정인가?
  - 이 use case에서 공통된 아이디어는 무엇인가?
  - 이 공통된 아이디어를 표현하기 위한 도메인 객체는 무엇인가?
- 도메인 객체는 일반적으로는 유용하지만 너무 일반적인 객체는 선택하지 말아야 한다.
  - 유지보수성과 복잡성 간의 trade-off관계이기 때문
- 일반적으로 모든 가능한 use case보다는 계획된 use case에만 집중해라.

```java
//good code
public void deliver(FoodOrder order){}

//bad code => 이건 모든 항목(심지어 가구)도 포함할 수 있다. YAGNI 원칙 위배(You aren't gonna need it)
public void deliver(DeliveryList items){}
```