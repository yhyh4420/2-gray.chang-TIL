서버가 클라이언트의 인증을 확인하는 방법은 쿠키, 세션, JWT가 있다.

## 쿠키
### 쿠키란 무엇인가?
* 클라이언트 컴퓨터 로컬에 유지하는 작은 웹 데이터
* (official docs)A cookie is a small piece of data a server sends to a user's web browser.
* 웹사이트가 사용자의 상태를 유지하는데 사용한다.
### 쿠키를 왜쓰는가?
* 기본적으로 http는 stateless 상태임. 즉, 서버가 클라이언트의 상태를 저장하지 않는다는 뜻이다.
* stateless는 많은 장점을 가지지만 이로 인해 로그인 정보를 유지할 수 없다는 단점이 생긴다.
  * 가령 로그인에 성공해도 다음 페이지에 넘어가는 순간 서버는 로그인을 했다는 사실을 유지하지 않으므로 다시 로그인을 하라고 요구할 수 있다.
* 그렇기 때문에 로그인에 성공하면 유저의 간단한 정보를 쿠키에 담아 매 request마다 request header에 탑재해서 보낸다.
### 쿠키 설정
* 생명주기 설정 가능(expires, max-age)  : expiers는 만료 날짜를 설정하고 max-age는 유효한 시간을 초단위로 설정하며 시간이 지나면 만료된다.
* 도메인 설정(domain) : 해당 도메인과 서브도메인에서 쿠키 접근 가능하게 설정(로그인하고 메인페이지에서 상세페이지로 넘어가도 쿠키 유지)
* 경로 설정((path) : 해당 경로를 포함한 하위 경로에서만 쿠키 접근
* 보안(Secure, HttpOnly, SameSite)
  * Secure : https에서만 전송
  * HttpOnly : XSS 방지, 자바스크립트 접근 불가, HTTP 전송에만 사용
  * SameSite : 요청 도메인과 쿠키에 설정된 도메인이 같은 경우에만 쿠키 전송

## 세션
### 세션이란 무엇인가?
* 세션은 사용자가 웹 애플리케이션과 상호작용하면서 유지되는 정보를 의미한다.
* 기본적인 세션은 서버에 저장되며, 클라이언트는 세션ID만 쿠키에 저장하고 매 request마다 쿠키에 실어서 전송한다.
### 세션의 작동원리
1. 로그인을 하면 서버에서 세션정보를 생성하여 응답 시 세션정보를 클라이언트에 보냄
2. 이후 페이지 이동할 때마다 쿠키에 세션ID를 포함하여 전송
3. 서버는 보유하고 있는 세션 스토리지에서 세션 정보를 확인하여 응답을 반환함
4. 만약 세션이 만료되면 스토리지에서 해당 세션을 삭제한다.
### 세션 저장 스토리지 종류
* 인메모리 : 서버 컴퓨터의 메모리에 저장하는 방법, 빠르지만 서버 재기동 시 데이터가 없어진다.
* 파일 스토리지 : 서버 컴퓨터에 파일 형식으로 저장하는 방법, 인메모리보다 느리지만 서버 재기동 시 데이터가 없어지지 않는다.
* 데이터베이스 : DBMS를 사용하는 방식으로 여러 서버에서 참조해도 무결성을 보장하짐만 세 방식중 제일 느리다.

## JWT
### JWT란 무엇인가?
* JSON Web Token, JSON 객체를 사용하여 정보를 전달함
* header.payload.signature 형식으로 구현되며, 로그인 시 서버에서 토큰을 생성하여 클라이언트에 전달한다.
### JWT원리
* 형식 : header.payload.signature
  * header : JWT에서 사용할 타입과 해시 알고리즘의 종류
  * payload : 실제 통신에 사용할 데이터들
    * payload의 key는 registered claim, public claim, private claim으로 나뉜다
      * registered claim : 미리 정의된 클레임
      * public claim : 사용자가 정의할 수 있는 클레임
      * private claim : 해당 유저를 특정할 수 있는 정보를 담는 클레임 
  * signature : 서버가 가지고 있는 비밀키로 서명한 전자서명
* 토큰이 탈취당하면 해당 토큰으로 사이트 접속이 가능하다. 그래서 토큰에 유효기간을 설정한 Refresh Token을 사용한다.
  * 서버에서 토큰을 보낼 때 일반 토큰(Access Token)과 리프레시 토큰을 같이 발급한다.
  * Access token이 만료되도 Refresh token이 살아있으면 Access token을 재발급해준다

----
오늘 팀 회고에서 나왔던 의문. daisy가 JWT 적용 프로젝트에서 DB를 쓴 경험이 있다고 해서 '그럼 세션이랑 기능이 동일하지 않나요?'라고 한 질문에 대해 leo가 좋은 포스팅을 공유해줬다.
### Access, Refresh 토큰을 DB에 저장해야 하는 이유([출처 : 티스토리 블로그](https://dev-jhl.tistory.com/entry/Spring-Security-JWT-Access-Refresh-%ED%86%A0%ED%81%B0%EC%9D%84-DB%EC%97%90-%EC%A0%80%EC%9E%A5%ED%95%B4%EC%95%BC%ED%95%98%EB%8A%94-%EC%9D%B4%EC%9C%A0))
생각보다 많은 사람들이 나같은 의문을 가지고 있는 것 같다.([stack overflow](https://stackoverflow.com/questions/42763146/does-it-make-sense-to-store-jwt-in-a-database), [Stack Exchange](https://softwareengineering.stackexchange.com/questions/373109/should-we-store-jwts-in-database))<br>
DB를 유지하는 이유는 JWT를 사용하면서 자동로그인 기능을 더 견고하게 유지하기 위해서라고 이해된다.
* 위 글에서 언급된 아키텍쳐를 대충 뜯어보자면, 각 서버에서 별도의 토큰DB를 참조하고, 이를 통해 서버가 다운되도 정보가 유지된다는 장점을 가져간다.
* 둘 다 결국 trade-off가 있고, 맹목적으로 기술을 사용하는 것 보다는 이유를 알고 쓰는것이 중요하다는 생각이 든다.