## HTTP Authorization Header
### HTTP 자격증명 헤더란?
- 클라이언튼와 서버의 인증과정에서 사용자의 자격을 확인하기 위해 사용되는 헤더
  - 인가와 인증을 구분해야 함
    - 인가(Authorization) : 사용자의 권한을 허용/거부하는 행위
    - 인증(Authentication) : 사용자의 서비스 접근을 허용/거부
- 왜 헤더에 계속 자격인증을 하는 정보를 실어서 보내는가? -> http는 stateless하기 때문
- 웹 리소스에 접근할 때 필요한 사용자의 인증 정보를 서버에 전달하기 위해 사용
- 인증 방식
  - 기본 인증
  - 다이제스트 인증
  - Bearer 토큰
  - OAuth
---
### 기본 인증
* 사용자 이름과 비밀번호를 ```:```로 구분한 뒤 base64로 인코딩한 값을 Authorization 헤더에 포함하여 전송
* base64 인코딩/디코딩 실습
    ```java
    public class BaseMain {
    
        public static void main(String[] args) {
            String username = "gray";
            String password = "1q2w3e4r!";
            String str = username + ":" + password;
            System.out.println("str = " + str);
            String encodeing = getEncodeing(str);
            String decodeing = getDecodeing(encodeing);
            /*
                str = gray:1q2w3e4r!
                encodeing = Z3JheToxcTJ3M2U0ciE=
                decoding = gray:1q2w3e4r!
             */
        }
    
        private static String getEncodeing(String str) {
            String encodeing = Base64.getEncoder().encodeToString(str.getBytes());
            System.out.println("encodeing = " + encodeing);
            return encodeing;
        }
    
        private static String getDecodeing(String str) {
            String decoding = new String(Base64.getDecoder().decode(str.getBytes()));
            System.out.println("decoding = " + decoding);
            return decoding;
        }
    }
    ```
* request 예시
    ```
    GET /protected/resource HTTP/1.1
    Host: test.com
    Authorization: Basic Z3JheToxcTJ3M2U0ciE=
    ```
* 단점 : base64로 단순 인코딩하여 중간자 공격에 취약함
  * 중간자 공격 : 해커가 사용자와 서버 중간에서 민감한 정보를 훔치는 공격


### 다이제스트 인증
* 서버가 인증에 필요한 정보를 클라이언트에 요구하면 이를 해시함수로 암호화하여 서버로 보내는 방식
* 클라이언트가 보호된 리소스에 접근하면 서버는 클라이언트에게 ```nonce```, ```realm``` 정보를 ```401 unauthorization``` 코드와 함께 보냄
  * nonce : number used once, 딱 한번만 쓰이는 숫자로 이걸 이용하여 해시함수를 생성
  * realm : 사용자가 로그인하려는 서비스의 범위
* 클라이언트는 서버로부터 받은 nonce, realm과 사용자 이름, 비밀번호, 요청 메서드, URI 등을 이용하여 해시함수로 다이제스트 응답을 생성
* 이 계산결과를 Authorization 헤더에 포함시켜 전송


### Bearer 토큰
* 토큰의 형태로 인증에 필요한 정보를 서버에 전송함
* 서버로부터 받은 Access Token을 Authorization 헤더에 포함하여 전송
* 예시 : JWT