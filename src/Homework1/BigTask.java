package Homework1;

import java.util.ArrayList;

class Fruit {
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

class Apple extends Fruit {
    private final int APPLE_WEIGHT = 1;

    public Apple() {
        setWeight(APPLE_WEIGHT);
    }
}

class Orange extends Fruit {
    private final int ORANGE_WEIGHT = 2;

    public Orange() {
        setWeight(ORANGE_WEIGHT);
    }
}

class Box<T extends Fruit> {
    private ArrayList<T> fruits;

    public Box(){
        fruits = new ArrayList<>();
    }
    public int getWeight() {
        int weight = 0;
        for (T elem : fruits) {
            weight += elem.getWeight();
        }
        return weight;
    }
    public boolean compare(Box<?> box) {
          return (this.getWeight() == box.getWeight());
    }

    public void add(T fruit){
        fruits.add(fruit);
    }

    public ArrayList<T> getFruits(){
        return fruits;
    }
    public void emptyBox(){
        fruits.clear();
    }
    public void addAll(Box<T> boxSource){
        fruits.addAll(boxSource.getFruits());
        boxSource.emptyBox();
    }

}

public class BigTask{

    public static void exchangeBoxes(Box boxSource, Box boxDest){

    }

    public static void main(String[] args) {

        Box<Apple> appleBox = new Box<>();
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        System.out.println(appleBox.getWeight());
        Box<Orange> orangeBox = new Box<>();
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        System.out.println(orangeBox.getWeight());
        System.out.println(orangeBox.compare(appleBox));

        orangeBox.add(new Orange());
        System.out.println(orangeBox.getWeight());
        System.out.println(orangeBox.compare(appleBox));

        Box<Orange> orangeBox2 = new Box<>();
        orangeBox2.add(new Orange());
        orangeBox.addAll(orangeBox2);

        System.out.println(orangeBox.getFruits().size());
        System.out.println(orangeBox2.getFruits().size());

    }
}