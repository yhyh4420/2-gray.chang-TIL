package org.til.exception;

public class ExceptionMain {

    public static void main(String[] args) {
        tryCatchEx1();
        System.out.println();
        tryCatchEx2();
        System.out.println();
        tryCatchEx3();
    }

    public static void tryCatchEx1(){
        //try-catch문법 기초. e라는 변수로 예외객체가 선언된 것을 확인할 수 있다. getMessage메서드를 통해 어떤 예외인지 확인할 수 있다.
        int a = 1;
        int c = 0;
        try{
            int d = a/c;
        } catch (ArithmeticException e){
            int d = 1;
            System.out.printf("0으로 못나눕니다. d값을 %d로 치환합니다.", d);
            System.out.println(e.getMessage()); // by zero
        }
    }

    public static void tryCatchEx2(){
        /*
        첫 catch에서 못잡아도 뒤의 catch가 예외를 잡을 수 있다.
        어떻게든 잡고 싶으면 마지막 catch를 부모클래스인 Exception으로 설정하면 잡힌다.
        다만 어떤 예외인지는 확인이 안된다.
         */
        int a = 1;
        int b = 2;
        int c = 0;
        try {
            int d = a/c;
        }catch (NumberFormatException e){
            int d = 1;
            System.out.println("숫자로 변환할 수 없습니다");
        }catch (ArithmeticException e){
            int d = 1;
            System.out.printf("0으로 못나눕니다. d값을 %d로 치환합니다", d);
        }
    }

    public static void tryCatchEx3(){
        /*
        finally는 예외처리 로직이 실행되어도 반드시 수행되어야 하는 로직들을 넣는다.
         */
        int a = 1;
        int c = 0;
        try {
            int d = a/c;
        } catch (ArithmeticException e){
            int d = 1;
            System.out.printf("0으로 못나눕니다. d값을 %d로 치환합니다.", d);
        }finally {
            int e = 4;
            System.out.printf("e값은 %d입니다.",e);
        }
    }
}
