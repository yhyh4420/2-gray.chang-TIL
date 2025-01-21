출처 : Java Virtual Machine Specification 공식 문서 및 기술블로그<br>
(https://docs.oracle.com/javase/specs/jvms/se8/html/index.html)<br>
(https://junuuu.tistory.com/126)


자바는 플랫폼에 구애받지 않는 프로그래밍 언어이다.<br>
즉, 안드로이드 어플, 웹, 임베디드 어느 플랫폼에도 구현이 가능하다는 뜻이다.<br> 
이런 호환성은 **JVM**이라는 기술에 의해 구현된다.<br>

## JVM이란?
JVM은 말 그대로 Virtual, 가상의 머신이고, 공식문서에서는 abstract computing machine, 추상적인 컴퓨팅 머신이라는 용어를 사용한다.<br>
특이한건 JVM은 자바 문법에 대해 아무것도 모르고, 특정 바이너리 포맷의 class 파일 포맷만 알고 있다.<br>
소스 자바 파일을 javac compiler에 의해 바이트파일(*.class)로 변환하게 되고, JVM은 이 클래스 파일을 읽게 된다.<br>

<img src="https://upload.wikimedia.org/wikipedia/commons/d/dd/JvmSpec7.png" width="60%" height="60%" align="center"></img><br>
## JVM의 구성요소
### 1. Class loader
 - JVM 아키텍쳐의 첫번째 구성요소, .class파일의 메모리를 Method Area에 로드하는 역할을 함
 - Loading -> Linking -> Initialization 과정을 거침
   1) Loading : 클래스를 로드함.(기본 자바 API라이브러리 -> 외부 라이브러리 -> 사용자 설정 경로 내 라이브러리 순)
   2) Linking : 검증(메모리로 로드된 바이트 코드가 스펙을 따르는지 검증), 준비(static 변수들의 기본값 할당), 해석(심볼릭 메모리 레퍼런스를 실제 메모리 주소로 교환)
   3) Initialization : 준비 단계에서 확보한 메모리 영역의 모든 정적 변수들에게 명시된 값 할당
### 2. Runtime Data Area
 - Class loader에 의해 로드된 클래스 파일이 Execution Engine에 의해 기계어로 해석되어 배치되는 공간
 - 세가지 영역(메서드, 힙, 스레드 영역)으로 구분됨
   1) 메서드 영역 : 클래스 멤버 변수의 이름, 데이터 타입, 접근 제어자 정보같은 필드 정보와 메서드 이름, 리턴 타입, 파라미트 등이 저장됨
   2) 힙 영역 :  모든 객체 및 인스턴스 변수 저장, Garbage Collection에 의해서만 메모리가 해제됨
   3) 스레드 영역 : 스택, 레지스터, 네이티브 메서드 스택으로 구성
      1) 스택 : 스레드 별로 생성, 메서드를 생성할때마다 스택에 해당 프레임이 추가되고, 종료되면 제거됨(push, pop)
      2) 레지스터 : 실행중인 메서드 안에서 몇번째 줄을 실행해야 하는지 나타내는 역할
      3) 네이티브 메서드 스택 : 자바가 아닌 다른 언어로 작성된 메서드
### 3. Execution Engine
 - 바이트 코드를 어셈블리어로 변환하고 명령어를 실행하는 실제 엔진
