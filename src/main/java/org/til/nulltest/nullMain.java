package org.til.nulltest;

public class nullMain {
    public static void main(String[] args) {
        NullObject testNullObject = null;

        //testNullObject.hello(); // NullPointException
        testNullObject.helloStatic(); // 경고는 해주지만 실행은 됨
    }
}
