DTO(Data Transfer Object), 말 그대로 데이터 전송 객체이다. 계층간 데이터 교환을 위해 사용하는 객체이다.<br>
여기서 계층이라는 것은 흔히 Spring MVC 패턴에서 Controller가 View와 Model과 데이터를 교환한다..할때 Controller, View, Model  등을 의미한다.<br>

여기서 의문점. 어차피 구현할 때 서비스를 위한 객체가 만들어진다. 흔히 쓰이는 Order, Member...그런데 이 객체들을 놔두고 굳이 DTO라는 객체를 새로 만드는 이유가 뭘까?<br>
일단 지금까지 찾은 DTO를 썼을 때 장점은 다음과 같다.<br>

1.민감한 도메인 정보를 노출하지 않고 렌더링할 View로 넘길 수 있다.<br>
2.Controller과 타 계층 간 결합도를 줄일 수 있다.
<img src="https://hudi.blog/static/9a11ff7dc9390be191f60fac775079fb/5c3fb/dto.png">
출처 : https://hudi.blog/data-transfer-object/

QA결과, DTO는 내가 이해한 것 처럼 계층간 자료 전달을 위한 역할을 하는 것이 맞다.<br>
다만, DTO를 사용하는 목적은 코드의 유지보수성 차원, 책임분리 목적으로 사용한다.<br>
(가독성 및 리뷰 측면의 직관성은 떨어질 것 같다는 것이 내 개인적인 의견)