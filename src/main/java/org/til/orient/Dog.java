package org.til.orient;

public class Dog extends Animal {
    public Dog(String species, String food) {
        super(species, food);
    }

    public Dog(String species) {
        super(species);
    }

    @Override
    public void bark() {
        System.out.println("I'm barking mungmung");
    }
}
