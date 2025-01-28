## HTTP Message
http message 공식 스펙은 다음과 같다.
```
start-line 
*( header-field CRLF )   
CRLF
[ message-body ]
# 참고 : CRLF는 CR과 LF의 합성어로 줄바꿈을 의미함
# *는 괄호 안의 내용이 0번 이상 반복됨을 의미한다
```
Request, Response 메세지 둘 다 이 공식 스펙을 따른다.
### Request
예시
```
GET /search?q=java HTTP/1.1
Host: www.google.com

<html>
.....
</html>
```

* start-line : method(공백)request-target(공백)HTTP-version(CRLF)
  * method : 서버가 수행해야 할 동작을 지정해줌(Get, Post, Put, Patch, Delete ...)
  * request-target : 요청 대상(절대경로 /로 시작하고, absolute-path?쿼리 방식으로 지정된다)
  * HTTP-version : 말그대로 http version을 명시한다.
* header-field : 요청 시 부가적인 정보를 보내는 헤더를 모아놓은 공간
  * header의 종류는 정말 많다. 뿐만 아니라 내가 원하는 헤더를 커스텀할 수도 있다.
* body : 보통 request에서는 body에 html을 넣어 보내지 않는다. 빈칸으로 보내지만 필요한 경우 body에 내용을 넣어 요청할 수 있다.

### Response
예시
```
HTTP/1.1 200 OK
Content-Type: text/htme;charset=UTF-8
Content-Length: 4254

<html>
    <body>
    ...
    </body>
<html> 
```
* start-line : http-version(공백)status-code(공백)reason-phrase
  * status-code : 어떤 응답인지 나타내는 코드(100대는 '요청이 접수되어 처리중'이라는 의미로 거의 안쓰임)
    * 200대(Success)
      * 200 OK : 요청이 성공함
      * 201 Created : 요청이 성공해서 새 리소스가 생성됨
      * 202 Accepted : (배치 처리 같은 곳에서) 요청이 받아들여졌으나 처리가 아직 완료되지 않았음
      * 204 No Content : 요청이 성공적이나 리소스가 없음(예시로 save같은 기능)
    * 300대(Redirection)
      * 301 Moved Permanetly, 308 Permanent Redirect : permanent redirect, 리소스의 URL이 영구적으로 이동함
        * 301은 request의 메서드가 get으로 변경되고, 308은 request의 메서드랑 바디가 변경되지 않음
      * 302 Found, 307 Temporary Redirect, 303 See Other : temporary redirect, 리소스의 URL이 일시적으로 변경됨
        * 302는 메서드가 Get으로 변할 수 있고 303은 메서드가 Get으로 변함, 307 메서드가 변하지 않음
        * 정확한 303이나 307을 권유하지만 실무적으로 이미 많은 애플리케이션에서 302를 사용함. Get으로 변해도 되면 302써도 무방(출처 : 인프런) 
    * 400대(Client Error)
      * 400 Bad Request : 메세지 문법 등이 맞지 않음
      * 401 Unauthorized : 사용자가 인증받지 않음(로그인이 안됨)
      * 403 Forbidden : 사용자가 인가받지 않음(일반 계정과 Admin 계정)
      * 404 Not found : 리소스가 없음
    * 500대(Server Error)
      * 500 Internet Server Error : 서버 내부 문제로 오류 발생 -> 애매하면 500
      * 503 Service Unavailable : 서비스가 과부하 및 작업으로 인해 요청을 처리할 수 없음

  * body : 실제 전송할 데이터, html이 예시이긴 하지만 json, 이미지, 영상 등 byte로 표현할 수 있는 모든 데이터 전송 가능