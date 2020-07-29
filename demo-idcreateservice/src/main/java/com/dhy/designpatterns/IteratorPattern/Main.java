package com.dhy.designpatterns.IteratorPattern;

public class Main {

    public static void main(String[] args) {

        MyCollection myList = new MyList();
        myList.add("a");
        myList.add("b");
        MyIterator iterator = myList.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
        }
    }
}
