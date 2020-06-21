package Homework1;

import java.util.ArrayList;
import java.util.List;

class Cat {
    private String name;

    public Cat(String catName) {
        this.name = catName;
    }

    public String getName() {
        return this.name;
    }
}

public class Lesson1<T> {
    private T[] objArray;

    public Lesson1(T... arr) {
        this.objArray = arr;
    }

    public void exchangeElements(int firstIndex, int secondIndex) {
        if ((firstIndex < objArray.length) && (secondIndex < objArray.length)) {
            T o = objArray[firstIndex];
            objArray[firstIndex] = objArray[secondIndex];
            objArray[secondIndex] = o;
        }
    }

    public T getElem(int index) {
        return objArray[index];
    }

    public List<T> getArrayList() {
        List<T> list = new ArrayList<>();
        for (T elem : objArray) {
            list.add(elem);
        }
        return list;
    }

}

class Test {
    public static void main(String[] args) {
        Lesson1<Cat> cats = new Lesson1<>(new Cat("first"), new Cat("second"));
        cats.exchangeElements(0, 1);
        System.out.println(((Cat) cats.getElem(0)).getName() + " " + ((Cat) cats.getElem(1)).getName());
        List<Cat> arrayList = cats.getArrayList();
        System.out.println(arrayList.get(0).getName());
    }
}
