package org.til.copy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CopyMain {
    public static void main(String[] args) {
        /*
        * 얕은 복사 : 주소를 복사함
        * 그렇기에 복사한 후 연산을 하면 복사된 객체와 복사한 객체 둘 다 변경됨
        */
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        List<String> list1 = list;
        list1.add("D");

        System.out.println("list = " + list);
        System.out.println("list1 = " + list1);
        System.out.println("-------------------");

        /*
         * 깊은 복사 : 값을 복사함
         * 그렇기에 복사한 후 연산을 하면 복사된 객체와 별개의 객체가 새 주소에 저장되며 별개의 연산이 가능함
         */
        List<String> list2 = new ArrayList<>(list);
        list2.add("E");
        list2.add("F");
        System.out.println("list = " + list);
        System.out.println("list2 = " + list2);
        System.out.println("--------------------------------------");
        //객체를 이용한 예시(얕은 복사)
        Member member = new Member("gray", 100);
        Member member1 = member;
        member.setAge(20);
        System.out.println(member.toString());
        System.out.println(member1.toString());
        System.out.println("-------------------");
        //객체를 이용한 예시(깊은 복사)
        Member member2 = new Member(member);
        member2.setAge(5000);
        System.out.println(member.toString());
        System.out.println(member2.toString());


    }
}
