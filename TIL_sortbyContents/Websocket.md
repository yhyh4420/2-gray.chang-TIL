웹소켓 프로토콜은 실시간 통신에 필요한 기술로, HTTP와 동일하게 어플리케이션 계층에서 동작한다.<br>
'실시간' 통신이라고 하면 웹소켓 기술을 사용한다고 생각하면 되겠다. 그 이전 http 기반 근실시간 통신 기법으로는 풀링이 있었다.<br>
웹소켓의 접속확립에는 http프로토콜을 사용하지만 그 이후에는 websoket 독자의 프로토콜을 사용한다. 여기서 말하는 접속확립을 handshake라고 한다.(3-way-handshake가 생각난다면 그 handshake가 맞다.)

### 웹소켓의 특징
* stateful : handshake 후 클라이언트와 서버는 연결을 유지한다.
* URL : https:// 가 아닌 ws://의 형식으로 사용된다.

### 웹소켓 Request, Response 예시
* Request
    ```
    GET /chat HTTP/1.1
    Host: www.test.com
    Connection: Upgrade
    Upgrade: websocket
    Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ==
    Sec-WebSocket-Version: 13
    ```
  * **Connection: Upgrade** - 클라이언트에서 프로토콜을 바꾸고 싶다는 것을 의미
  * **Upgrade : websocket** - 클라이언트가 프로토콜을 웹소켓으로 바꾸고 샆다는 것을 의미
  * **Sec-Websocket-key** - 보안을 위해 브라우저가 생성한 키, 서버가 웹소켓을 지원하는지 확인함
  * **Sec-WebSocket=Version** - 웹소켓 프로토콜 버전

* Response
    ```
    101 Switching Protocols
    Upgrade: websocket
    Connection: Upgrade
    Sec-WebSocket-Accept: hsBYongNyong24s99EO10UlZ22C2g=
    ```
  * 101 Switching Protocols : 연결을 수락하는 응답코드



### 웹소켓이 필요한 경우
* 실시간 양방향 통신이 필요한 경우
* 한번에 많은 수의 사용자를 동시 수용해야 할 경우

### Socket.io
웹소켓은 HTML5의 기술이므로 오래된 버전의 브라우저에서는 지원하지 않는다.(explorer..)
그래서 웹소켓을 지원하지 않는 브라우저의 경우 Socket.io 기술을 사용하여 실시간 통신을 흉내낸다.
* node.jjs 기반으로 만들어진 기술이고, 현존하는 대부분의 실시간 통신 기술을 추상화해놨다.
* javaScript를 이용하여 브라우저 종류에 상관없이 실시간 웹을 구현할 수 있다.

### 웹소켓 사용 시 유의할 점
* 비정상적으로 연결이 끊어졌을 때 적절하게 대응하게 구현해야 한다.
* 연결이 끊어졌을 때 에러메세지가 구체적이지 않아 디버깅 시 어려움이 많다.
* 소켓 연결 자체가 리소스를 많이 소모한다.

