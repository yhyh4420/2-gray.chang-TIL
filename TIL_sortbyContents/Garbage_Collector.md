[참고사이트](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EA%B0%80%EB%B9%84%EC%A7%80-%EC%BB%AC%EB%A0%89%EC%85%98GC-%EB%8F%99%EC%9E%91-%EC%9B%90%EB%A6%AC-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%F0%9F%92%AF-%EC%B4%9D%EC%A0%95%EB%A6%AC#%EA%B0%80%EB%B9%84%EC%A7%80_%EC%BB%AC%EB%A0%89%EC%85%98_%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98_%EC%A2%85%EB%A5%98)

가비지 컬렉션(Garbage Collection, 이하 GC) : 힙 영역에서 더이상 사용하지 않는 메모리를 정리하는 역할이다.<br>
기본적으로 Stop-the-world(이하 STW) 후 Mark-Sweep을 실시하는 과정으로 수행된다.<br>
 * STW : GC동작을 하기 위해서는 GC를 위한 스레드 외 시행되고 있는 JVM을 중단시켜야 한다. 말 그대로 세상이 멈추기 때문에 STW라고 한다.
 * Mark-Sweep : 힙 영역을 순회하면서 GC대상의 메모리들을 정리한 후 살아있는 메모리를 다시 정렬하여 중간중간 빈 메모리가 없게 한다.


GC를 이해하기 위해서는 힙 영역에 대한 이해가 선행되어야 한다. GC를 효율적으로 하기 위해 힙 영역을 물리적으로 두 영역으로 나눈다.<br>
* Young 영역 : 새롭게 객체가 할당되는 영역
  * 대부분의 객체가 금방 접근불가 상태가 되기 때문에(Generation hypothesis) 많은 객체가 여기서 생성되고 GC에 의해 사라진다.
  * 여기서 이루어지는 GC를 Minor GC라고 한다.
  * Young 영역은 또다시 세가지 영역으로 나뉜다.(Eden, survive 0,survive 1)
    * Eden : 새로 생성되는 객체가 최초 저장되는 영역
      * Minor GC가 수행되는 시점은 바로 이 Eden영역이 가득 찼을 때다. 
      * 여기서 수행되는 GC에서 살아남으면 survive 구역으로 이동하게 된다.
    * survive 0/1 : 최소 1번의 GC에서 살아남은 Eden 내 객체가 넘어오는 공간
      * 둘 중 하나는 무조건 비어있어야 하며, GC가 이루어질때마다 0->1, 1->0으로 이동한다.
  * 일반적으로 GC가 수행될 때마다 age += 1를 한다.
  * 일반적인 JVM에서 age의 임계값은 31이다. age가 임계값을 넘게되면 Old 영역으로 이동하게 된다
  * Minor GC는 빠르다.
  
    
* Old 영역 : Young 영역에서 살아남은 객체들이 이동하는 구역
  * 여기서 시행되는 GC를 Major GC라고 함
  * Major GC는 느리다. 따라서 GC 튜닝 시 Major GC 시간을 줄이는 것이 관건이다.
