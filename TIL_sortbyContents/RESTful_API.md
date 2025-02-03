## REST API란?
- Representation State Transfer, 자원을 이름으로 구분하여 해당 자원의 상태를 주고받는 모든 것
- 즉 HTTP URI로 자원을 명시하고 HTTP Method를 통해 자원에 어떤 행위를 할것인지 규정 -> CRUD Operation을 적용함

### Rest 구성요소
 - Resource(자원) : Http URI
 - Verb(자원에 대한 행위) : Http Method
 - Representation(자원에 대한 행위의 내용) : Http Message payload

각 Method별 올바른 행위에 대한 매핑

| Method |                      역할                       |
|:------:|:---------------------------------------------:|
|  POST  |        POST를 통해 해당 URI를 요청하면 리소스를 생성함         |
|  GET   | GET을 통해 해당 리소스를 조회, 해당 document에서 자세한 정보를 가져옴 |
|  PUT   |              PUT을 통해 해당 리소스를 수정               |
| PATCH  |   해당 리소스를 수정하는 것은 동일하지만, 일부분만 수정하는 용도로 사용함    |
| DELETE |             DELETE를 통해 해당 리소스를 삭제             |

이런 REST 원칙을 잘 지켜 구현한 API를 RESTful API라고 한다. 많은 기업의 api제공 서비스의 document를 보면 REST API에 대한 정의를 내린다.
[참고 : 카카오 로그인 api](https://developers.kakao.com/docs/latest/ko/rest-api/reference)

잘 구현된 API의 예시는 다음과 같다.```GET /boards``` 처럼 보기만 해도 boards라는 곳(아마 게시판)에서 정보를 검색하는구나. 라고 생각이 들어야 한다.
스프링의 경우 ```@GetMaapping```, ```@PostMaapping```과 같은 Annotation을 컨트롤러 구현 메서드에 넣어 REST API를 구현한다.