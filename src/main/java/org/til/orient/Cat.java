package org.til.orient;

public class Cat extends Animal {
    public Cat(String species, String food) {
        super(species, food);
    }

    public Cat(String species) {
        super(species);
    }

    @Override
    public void bark() {
        System.out.println("I'm barking meow");
    }
}