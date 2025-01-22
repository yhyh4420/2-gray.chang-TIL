package org.til.orient;

public class Human extends Animal {

    public Human(String species, String food) {
        super(species, food);
    }

    public Human(String species) {
        super(species);
    }

    @Override
    public void bark() {
        System.out.println("I do not bark.");
    }
}
