package org.til.orient;

public class Animal {

    private String species;
    private String food;

    public Animal(String species, String food) {
        this.species = species;
        this.food = food;
    }

    public Animal(String species) {
        this.species = species;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void sayHello() {
        System.out.println("hello, I'm " + species + "!");
    }

    public void eat() {
        System.out.println("I'm eating " + food + "!");
    }

    public void bark(){
        System.out.println("I'm barking");
    }
}
