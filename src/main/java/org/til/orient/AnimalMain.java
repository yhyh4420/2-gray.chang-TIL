package org.til.orient;

public class AnimalMain {
    public static void main(String[] args) {
        Dog dog = new Dog("푸들", "meat");
        Cat cat = new Cat("코리안숏헤어", "fish");
        Human human = new Human("인간", "all food");
        dog.bark();
        dog.eat();
        dog.sayHello();
        System.out.println("--------------------");
        cat.bark();
        cat.eat();
        cat.sayHello();
        System.out.println("--------------------");
        human.bark();
        human.eat();
        human.sayHello();
    }
}
