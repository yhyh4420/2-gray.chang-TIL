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
  - Bearer 인증
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


### Bearer 인증
[토스페이먼츠 기술블로그](https://docs.tosspayments.com/resources/glossary/bearer-auth#%ED%86%A0%EC%8A%A4%ED%8E%98%EC%9D%B4%EB%A8%BC%EC%B8%A0-bearer-%EC%9D%B8%EC%A6%9D)
* OAuth 2.0 프레임워크에서 사용하는 토큰 기반 인증 방식
* 토큰의 형태로 인증에 필요한 정보를 서버에 전송함
  * 토큰의 형태는 JWT일 수도 있고, 인코딩된 문자열일 수도 있음
  * 서버 입장에서는 토큰을 발급만 해주고 보관할 필요가 없음.
  * 하지만 탈취당하면 탈취한 사람이 토큰을 사용할 수 있다는 보안적 단점이 존재하는데, 이것을 해결하기 위해서 각종 보안조치를 한다.
    * (클라이언트 기준) 수정, 삭제 등 중요한 요청 시 매번 랜덤의 CSRF 토큰을 생성하여 이 토큰도 request에 같이 보내서 검증함.
      * Spring Security 사용 시 기본값으로 적용된다.
    * Cookie SameSite 옵션 설정, referer check(request 보낸 도메인 확인)을 통해 쿠키 탈취에 대응할 수 있다.
    * (서버 기준) Access Token의 수명을 짧게 만들어 재발급 시 Refresh Token을 같이 제시해야 토큰 재발급을 허용하는 로직을 구현할 수 있다.
* 서버로부터 받은 Access Token을 Authorization 헤더에 포함하여 전송