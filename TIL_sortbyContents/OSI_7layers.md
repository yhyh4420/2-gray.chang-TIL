## OSI 7계층이란?
 - ISO에서 개발한 컴퓨터 네트워크 프로토콜 모델

## 계층을 분리한 이유?
- 개발 및 유지보수의 용이성
- 각 계층의 모듈화로 문제 발생 시 각 계층에서 해결 가능함
- 계층적인 구조를 가짐으로서 각 계층이 추상화된 인터페이스처럼 되어 각 속성을 상속받는 계층별 기술이나 장비를 도입하기 쉬워짐

## 각 계층별 설명

### 물리 계층
- 아날로그 전자기파를 디지털 컴퓨터 신호로 디코딩하고, 컴퓨터 신호를 전자기파로 인코딩하는 역할
- 데이터 전송단위 : Bit

### 데이터링크 계층
- 물리 계층의 주소 체계나 데이터가 변조되지 않았음을 검증하는 역할
- 전송단위 : 프레임

### 네트워크 계층
- IP 주소를 관리하고 원하는 목적지까지 패킷을 보내기 위해 라우팅 알고리즘을 적용함
- 전송단위 : 패킷

### 전송 계층
- 포트번호를 관리하여 수신된 데이터가 어느 응용프로그램에 전송될지 판독함
- 송신측과 수신측의 연결을 관리
- 대표적인 알고리즘으로는 TCP, UDP가 있다.
- 데이터 단위 : 세그먼트

### 세션 계층
- 표현 계층으로부터 데이터를 받아 더 잘게 나누거나(세그먼트) 전송 계층으로부터 세그먼트를 받아 데이터로 재조립하는 역할

### 표현 계층
- 응용 계층의 데이터를 하위 계층이 이해하기 쉬운 포맷으로 변환하거나 그 반대의 과정을 수행

### 응용 계층
- 사용자가 직접 상호작용하는 소프트웨어를 지원